package com.bumptech.glide.samples.svg

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import com.bumptech.glide.request.target.Target
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import java.io.IOException
import java.io.InputStream

/**
 * Decodes an SVG internal representation from an [InputStream].
 */
class SvgDecoder : ResourceDecoder<InputStream, SVG> {
    override fun handles(
        source: InputStream,
        options: Options,
    ): Boolean = true

    @Throws(IOException::class)
    override fun decode(
        source: InputStream,
        width: Int,
        height: Int,
        options: Options,
    ): Resource<SVG>? =
        try {
            val svg = SVG.getFromInputStream(source)
            if (width != Target.SIZE_ORIGINAL) {
                svg.documentWidth = width.toFloat()
            }
            if (height != Target.SIZE_ORIGINAL) {
                svg.documentHeight = height.toFloat()
            }
            SimpleResource(svg)
        } catch (ex: SVGParseException) {
            throw IOException("Cannot load SVG from stream", ex)
        }
}
