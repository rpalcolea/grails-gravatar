package rpalcolea.gravatar

import grails.test.mixin.integration.Integration
import org.grails.buffer.GrailsPrintWriter
import org.grails.gsp.GroovyPagesTemplateEngine
import org.grails.plugins.testing.GrailsMockHttpServletRequest
import org.grails.plugins.testing.GrailsMockHttpServletResponse
import org.grails.web.servlet.DefaultGrailsApplicationAttributes
import org.grails.web.servlet.mvc.GrailsWebRequest
import org.springframework.web.context.request.RequestContextHolder
import spock.lang.Specification

import javax.servlet.ServletContext

@Integration
class GravatarTagLibSpec extends Specification {

    private GrailsMockHttpServletRequest request = new GrailsMockHttpServletRequest()
    private GrailsMockHttpServletResponse response = new GrailsMockHttpServletResponse()

    ServletContext servletContext
    GroovyPagesTemplateEngine groovyPagesTemplateEngine

    void 'Should render the tag by only providing the email'() {
        expect:
        assertOutputEquals '<img id="" name="" alt="Gravatar" class="gravatar" height="80" width="80" src="https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=80&r=g" title="gravatar"/>',
                '<gravatar:image email="roberto@perezalcolea.info"/>'

    }

    void 'Should render the tag when user provides email, size and rating'() {
        expect:
        assertOutputEquals '<img id="" name="" alt="Gravatar" class="gravatar" height="20" width="20" src="https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=x" title="gravatar"/>',
                '<gravatar:image email="roberto@perezalcolea.info" size="20" gravatarRating="x"/>'

    }

    void 'Should render the tag when user provides email, size, rating and defaultGravatarUrl'() {
        expect:
        assertOutputEquals '<img id="" name="" alt="Gravatar" class="gravatar" height="20" width="20" src="https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=x&d=404" title="gravatar"/>',
                '<gravatar:image email="roberto@perezalcolea.info" size="20" gravatarRating="x" defaultGravatarUrl="404"/>'

        assertOutputEquals '<img id="" name="" alt="Gravatar" class="gravatar" height="20" width="20" src="https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=x&d=identicon" title="gravatar"/>',
                '<gravatar:image email="roberto@perezalcolea.info" size="20" gravatarRating="x" defaultGravatarUrl="identicon"/>'

        assertOutputEquals '<img id="" name="" alt="Gravatar" class="gravatar" height="20" width="20" src="https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=x&d=mm" title="gravatar"/>',
                '<gravatar:image email="roberto@perezalcolea.info" size="20" gravatarRating="x" defaultGravatarUrl="mm"/>'

        assertOutputEquals '<img id="" name="" alt="Gravatar" class="gravatar" height="20" width="20" src="https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=x&d=monsterid" title="gravatar"/>',
                '<gravatar:image email="roberto@perezalcolea.info" size="20" gravatarRating="x" defaultGravatarUrl="monsterid"/>'

        assertOutputEquals '<img id="" name="" alt="Gravatar" class="gravatar" height="20" width="20" src="https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=x&d=wavatar" title="gravatar"/>',
                '<gravatar:image email="roberto@perezalcolea.info" size="20" gravatarRating="x" defaultGravatarUrl="wavatar"/>'

        assertOutputEquals '<img id="" name="" alt="Gravatar" class="gravatar" height="20" width="20" src="https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=x&d=retro" title="gravatar"/>',
                '<gravatar:image email="roberto@perezalcolea.info" size="20" gravatarRating="x" defaultGravatarUrl="retro"/>'

        assertOutputEquals '<img id="" name="" alt="Gravatar" class="gravatar" height="20" width="20" src="https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=20&r=x&d=http://docs.grails.org/latest/img/grails.png" title="gravatar"/>',
                '<gravatar:image email="roberto@perezalcolea.info" size="20" gravatarRating="x" defaultGravatarUrl="http://docs.grails.org/latest/img/grails.png"/>'
    }

    /**
     *
     * @param expected
     * @param template
     */
    private void assertOutputEquals(String expected, String template) {
        def sw = new StringWriter()
        def out = new GrailsPrintWriter(sw)
        GrailsWebRequest grailsWebRequest = new GrailsWebRequest(request, response,
                new DefaultGrailsApplicationAttributes(servletContext))
        grailsWebRequest.out = out
        RequestContextHolder.requestAttributes = grailsWebRequest
        groovyPagesTemplateEngine.createTemplate(template, 'test_' + UUID.randomUUID()).make([:]).writeTo out
        assert expected == sw.toString()
    }
}
