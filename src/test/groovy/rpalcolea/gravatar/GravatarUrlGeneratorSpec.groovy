package rpalcolea.gravatar

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class GravatarUrlGeneratorSpec extends Specification {

    void "Generate URL with only email as input"() {
        given:
        String email = "roberto@perezalcolea.info"

        when:
        String gravatarUrl = GravatarUrlGenerator.generateUrl(email)

        then:
        gravatarUrl == "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=80&r=g"
    }

    void "Generate URL providing email, rating and size"() {
        when:
        String gravatarUrl = GravatarUrlGenerator.generateUrl(email, gravatarRating, size)

        then:
        gravatarUrl == expectedResult

        where:
        email                       | gravatarRating                             | size | expectedResult
        "roberto@perezalcolea.info" | GravatarRating.RESTRICTED                  | 20   | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=r"
        "roberto@perezalcolea.info" | GravatarRating.PARENTAL_GUIDANCE_SUGGESTED | 20   | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=pg"
        "roberto@perezalcolea.info" | GravatarRating.XPLICIT                     | 20   | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=x"
        "roberto@perezalcolea.info" | GravatarRating.XPLICIT                     | null | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=80&r=x"
        "roberto@perezalcolea.info" | null                                       | null | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=80&r=g"
        "roberto@perezalcolea.info" | null                                       | 20   | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=g"
    }

    void "Generate URL providing all the possible parameters"() {
        when:
        String gravatarUrl = GravatarUrlGenerator.generateUrl(email, gravatarRating, size, defaultImage)

        then:
        gravatarUrl == expectedResult

        where:
        email                       | gravatarRating                             | size | defaultImage                                   | expectedResult
        "roberto@perezalcolea.info" | GravatarRating.RESTRICTED                  | 20   | null                                           | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=r"
        "roberto@perezalcolea.info" | GravatarRating.PARENTAL_GUIDANCE_SUGGESTED | 20   | null                                           | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=pg"
        "roberto@perezalcolea.info" | GravatarRating.XPLICIT                     | 20   | null                                           | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=x"
        "roberto@perezalcolea.info" | GravatarRating.XPLICIT                     | null | null                                           | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=80&r=x"
        "roberto@perezalcolea.info" | null                                       | null | null                                           | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=80&r=g"
        "roberto@perezalcolea.info" | null                                       | 20   | null                                           | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=g"
        "roberto@perezalcolea.info" | GravatarRating.RESTRICTED                  | 20   | "mm"                                           | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=r&d=mm"
        "roberto@perezalcolea.info" | GravatarRating.RESTRICTED                  | 20   | "404"                                          | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=r&d=404"
        "roberto@perezalcolea.info" | GravatarRating.RESTRICTED                  | 20   | "identicon"                                    | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=r&d=identicon"
        "roberto@perezalcolea.info" | GravatarRating.RESTRICTED                  | 20   | "monsterid"                                    | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=r&d=monsterid"
        "roberto@perezalcolea.info" | GravatarRating.RESTRICTED                  | 20   | "wavatar"                                      | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=r&d=wavatar"
        "roberto@perezalcolea.info" | GravatarRating.RESTRICTED                  | 20   | "retro"                                        | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=r&d=retro"
        "roberto@perezalcolea.info" | GravatarRating.RESTRICTED                  | 20   | "http://docs.grails.org/latest/img/grails.png" | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=r&d=http://docs.grails.org/latest/img/grails.png"
        "roberto@perezalcolea.info" | GravatarRating.RESTRICTED                  | null | "http://docs.grails.org/latest/img/grails.png" | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=80&r=r&d=http://docs.grails.org/latest/img/grails.png"
        "roberto@perezalcolea.info" | null                                       | null | "http://docs.grails.org/latest/img/grails.png" | "https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=80&r=g&d=http://docs.grails.org/latest/img/grails.png"
    }

    void "Should throw exception if email is not provided"() {
        when:
        String gravatarUrl = GravatarUrlGenerator.generateUrl(null)

        then:
        RuntimeException ex = thrown()
        ex.message == 'Email can not be empty or null'
    }
}
