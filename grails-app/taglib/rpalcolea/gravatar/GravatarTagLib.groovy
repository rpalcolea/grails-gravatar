package rpalcolea.gravatar

/**
 * Source taken from https://raw.githubusercontent.com/domix/avatarplugin/master/grails-app/taglib/com/synergyj/grails/plugins/avatar/AvatarTagLib.groovy
 * Created by
 *  @domix
 *  @ilopmar
 *  @neodevelop
 *  @dima767
 */
class GravatarTagLib {

    static namespace = "gravatar"

    /**
     * Outputs the Gravatar this is associated with the given email address
     * Note the parameter documentation at http://de.gravatar.com/site/implement/images/
     *
     * @attr email    REQUIRED	the startDate for styling
     * @attr size          the disired dimensions in pixels for the gravatar image (from 1 up to 512)
     * @attr alt          alt-attribute for the resulting img-element
     * @attr cssClass        class-attribute for the resulting img-element
     * @attr title          title-attribute for the resulting img-element
     * @attr id            id-attribute for the resulting img-element
     * @attr name          name-attribute for the resulting img-element
     * @attr defaultGravatarUrl    the default image to display if no gravatar is found; may be a URL or one of the following (defaults to the official Gravatar logo):
     * 								<li>404: do not load any image if none is associated with the email hash, instead return an HTTP 404 (File Not Found) response
     * 								<li>mm: (mystery-man) a simple, cartoon-style silhouetted outline of a person (does not vary by email hash)
     * 								<li>identicon: a geometric pattern based on an email hash
     * 								<li>monsterid: a generated 'monster' with different colors, faces, etc
     * 								<li>wavatar: generated faces with differing features and backgrounds
     * 								<li>retro: awesome generated, 8-bit arcade-style pixelated faces
     * @attr gravatarRating      desired image rating censor-level; may be one of the following:
     * 								<li>g (default): suitable for display on all websites with any audience type.
     * 								<li>pg: may contain rude gestures, provocatively dressed individuals, the lesser swear words, or mild violence.
     * 								<li>r: may contain such things as harsh profanity, intense violence, nudity, or hard drug use.
     * 								<li>x: may contain hardcore sexual imagery or extremely disturbing violence.
     */
    def image = {  attrs, body ->
        if (!attrs.email) throw new IllegalStateException("Property [email] must be set!")
        String email = attrs.remove('email').toString()
        Integer size = (attrs.remove('size') ?: 80) as Integer
        String alt = attrs.remove('alt') ?: "Gravatar"
        String cssClass = attrs.remove('cssClass') ?: "gravatar"
        String title = attrs.remove('title') ?: 'gravatar'
        String id = attrs.remove('id') ?: ''
        String name = attrs.remove('name') ?: ''
        String dgu = (attrs.remove('defaultGravatarUrl') ?: grailsApplication.config.gravatar.defaultGravatarUrl) ?: ''
        String gravatarRating = attrs.remove('gravatarRating') ?: grailsApplication.config.gravatar.defaultRating ?: 'g'

        GravatarRating rating = GravatarRating.get(gravatarRating)

        String gravatarUrl = GravatarUrlGenerator.generateUrl(email, rating, size, dgu)

        out << """<img id="${id}" name="${name}" alt="$alt" class="$cssClass" height="$size" width="$size" src="$gravatarUrl" title="$title"/>"""
    }
}
