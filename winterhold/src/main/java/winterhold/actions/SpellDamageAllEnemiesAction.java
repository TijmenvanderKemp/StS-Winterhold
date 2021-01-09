package winterhold.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import winterhold.spelldamage.SpellDamageTracker;
import winterhold.spelldamage.SpellDamageType;

public class SpellDamageAllEnemiesAction
        extends AbstractGameAction {
    public int[] damage;
    private int baseDamage;
    private boolean firstFrame = true, utilizeBaseDamage = false;
    private final SpellDamageType spellDamageType;

    public SpellDamageAllEnemiesAction(AbstractCreature source, int[] amount, SpellDamageType spellDamageType, DamageInfo.DamageType type, AbstractGameAction.AttackEffect effect, boolean isFast) {
        this.source = source;
        this.damage = amount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.spellDamageType = spellDamageType;
        this.damageType = type;
        this.attackEffect = effect;
        if (isFast) {
            this.duration = Settings.ACTION_DUR_XFAST;
        } else {
            this.duration = Settings.ACTION_DUR_FAST;
        }
    }

    public SpellDamageAllEnemiesAction(AbstractCreature source, int[] amount, SpellDamageType spellDamageType, DamageInfo.DamageType type, AbstractGameAction.AttackEffect effect) {
        this(source, amount, spellDamageType, type, effect, false);
    }

    public SpellDamageAllEnemiesAction(AbstractPlayer player, int baseDamage, SpellDamageType spellDamageType, DamageInfo.DamageType type, AbstractGameAction.AttackEffect effect) {
        this(player, null, spellDamageType, type, effect, false);
        this.baseDamage = baseDamage;
        this.utilizeBaseDamage = true;
    }


    public void update() {
        if (this.firstFrame) {
            boolean playedMusic = false;
            int temp = (AbstractDungeon.getCurrRoom()).monsters.monsters.size();
            if (this.utilizeBaseDamage) {
                this.damage = DamageInfo.createDamageMatrix(this.baseDamage);
            }

            for (int i = 0; i < temp; i++) {
                if (!(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).isDying &&
                        (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).currentHealth > 0 &&
                        !(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).isEscaping) {
                    if (playedMusic) {
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(

                                (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).hb.cX,
                                (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).hb.cY, this.attackEffect, true));
                    } else {

                        playedMusic = true;
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(

                                (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).hb.cX,
                                (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).hb.cY, this.attackEffect));
                    }
                }
            }

            this.firstFrame = false;
        }

        tickDuration();

        if (this.isDone) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                p.onDamageAllEnemies(this.damage);
            }

            int temp = (AbstractDungeon.getCurrRoom()).monsters.monsters.size();
            for (int i = 0; i < temp; i++) {
                AbstractMonster monster = (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i);
                if (!monster.isDeadOrEscaped()) {
                    if (this.attackEffect == AbstractGameAction.AttackEffect.POISON) {
                        monster.tint.color.set(Color.CHARTREUSE);
                        monster.tint.changeColor(Color.WHITE.cpy());
                    } else if (this.attackEffect == AbstractGameAction.AttackEffect.FIRE) {
                        monster.tint.color.set(Color.RED);
                        monster.tint.changeColor(Color.WHITE.cpy());
                    }
                    SpellDamageTracker.INSTANCE.dealDamage(spellDamageType);
                    SpellDamageTracker.INSTANCE.setInDamagePhaseOfElementalAttack(true);
                    monster.damage(new DamageInfo(this.source, this.damage[i], this.damageType));
                    SpellDamageTracker.INSTANCE.setInDamagePhaseOfElementalAttack(false);
                    SpellDamageTracker.INSTANCE.publishCombo();
                }
            }


            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
            if (!Settings.FAST_MODE)
                addToTop(new WaitAction(0.1F));
        }
    }
}
