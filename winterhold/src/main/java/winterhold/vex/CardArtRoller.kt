package winterhold.vex

import basemod.abstracts.CustomCard
import basemod.patches.whatmod.WhatMod
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import com.github.tommyettinger.colorful.Shaders
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.helpers.CardLibrary
import com.megacrit.cardcrawl.random.Random
import java.util.*

object CardArtRoller {
    private val doneCards: MutableMap<String, TextureAtlas.AtlasRegion> = HashMap()
    private var infos: MutableMap<String, ReskinInfo> = HashMap()
    private val shade = ShaderProgram(Shaders.vertexShaderHSLC, Shaders.fragmentShaderHSLC)
    fun computeCard(c: CustomCard) {
        c.portrait = doneCards.computeIfAbsent(c.cardID) { key: String ->
            val r = infos.computeIfAbsent(key) {
                val rng = Random(
                    c.cardID.hashCode().toLong()
                )
                val cardsList = Wiz.getCardsMatchingPredicate(
                    { s: AbstractCard -> s.type == c.type && WhatMod.findModName(s.javaClass) == null },
                    true
                )
                val q = Wiz.getRandomItem(cardsList, rng).cardID
                ReskinInfo(
                    q,
                    rng.random(0.35f, 0.65f),
                    rng.random(0.35f, 0.65f),
                    rng.random(0.35f, 0.65f),
                    rng.random(0.35f, 0.65f),
                    rng.randomBoolean()
                )
            }
            val hslc = Color(r.h, r.s, r.l, r.c)
            val t = CardLibrary.getCard(r.origCardID).portrait
            t.flip(false, true)
            val fb = ImageHelper.createBuffer(250, 190)
            val og = OrthographicCamera(250F, 190F)
            val sb = SpriteBatch()
            sb.projectionMatrix = og.combined
            ImageHelper.beginBuffer(fb)
            sb.shader = shade
            sb.color = hslc
            sb.begin()
            sb.draw(t, -125f, -95f)
            sb.end()
            fb.end()
            t.flip(false, true)
            val a = ImageHelper.getBufferTexture(fb)
            TextureAtlas.AtlasRegion(a.texture, 0, 0, 250, 190)
        }
    }

    fun getPortraitTexture(c: AbstractCard): Texture {
        val r = infos[c.cardID]!!
        val hslc = Color(r.h, r.s, r.l, r.c)
        val t = TextureAtlas.AtlasRegion(
            TexLoader.getTexture(
                "images/1024Portraits/" + CardLibrary.getCard(
                    r.origCardID
                ).assetUrl + ".png"
            ), 0, 0, 500, 380
        )
        t.flip(false, true)
        val fb = ImageHelper.createBuffer(500, 380)
        val og = OrthographicCamera(500F, 380F)
        val sb = SpriteBatch()
        sb.projectionMatrix = og.combined
        ImageHelper.beginBuffer(fb)
        sb.shader = shade
        sb.color = hslc
        sb.begin()
        sb.draw(t, -250f, -190f)
        sb.end()
        fb.end()
        t.flip(false, true)
        val a = ImageHelper.getBufferTexture(fb)
        return a.texture

        //Actually, I think this can work. Because SingleCardViewPopup disposes of the texture, we can just make a new one every time.
    }

    data class ReskinInfo(
        var origCardID: String,
        var h: Float,
        var s: Float,
        var l: Float,
        var c: Float,
        var flipX: Boolean
    )
}