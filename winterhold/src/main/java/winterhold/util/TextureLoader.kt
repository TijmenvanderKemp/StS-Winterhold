package winterhold.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.utils.GdxRuntimeException
import org.apache.logging.log4j.LogManager
import java.util.*

object TextureLoader {
    private val textures = HashMap<String, Texture?>()
    private val logger = LogManager.getLogger(TextureLoader::class.java.name)

    /**
     * @param textureString - String path to the texture you want to load relative to resources,
     * Example: "theDefaultResources/images/ui/missing_texture.png"
     * @return [com.badlogic.gdx.graphics.Texture] - The texture from the path provided
     */
    fun getTexture(textureString: String): Texture {
        if (textures[textureString] == null) {
            try {
                textures[textureString] = loadTexture(textureString)
            } catch (e: GdxRuntimeException) {
                logger.error("Could not find texture: $textureString")
                return getTexture("winterholdResources/images/ui/missing_texture.png")
            }
        }
        return textures[textureString] ?: throw Exception("Texture not found in textureMap: $textureString")
    }

    /**
     * Creates an instance of the texture, applies a linear filter to it, and places it in the HashMap
     *
     * @param textureString - String path to the texture you want to load relative to resources,
     * Example: "img/ui/missingtexture.png"
     * @throws GdxRuntimeException
     */
    @Throws(GdxRuntimeException::class)
    private fun loadTexture(textureString: String): Texture {
        logger.info("DefaultMod | Loading Texture: $textureString")
        val texture = Texture(textureString)
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear)
        return texture
    }
}