package rpalcolea.gravatar

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.commons.lang.StringUtils

import java.security.MessageDigest

@Slf4j
@CompileStatic
class GravatarUrlGenerator {

    private final static int DEFAULT_SIZE = 80
    private final static String GRAVATAR_URL = "https://www.gravatar.com/avatar/"
    private static final GravatarRating DEFAULT_RATING = GravatarRating.GENERAL_AUDIENCES

    public static String generateUrl(String email) {
        generateUrl(email, DEFAULT_RATING, DEFAULT_SIZE, null)
    }

    public static String generateUrl(String email, GravatarRating rating, Integer size) {
        generateUrl(email, rating, size, null)
    }

    public static String generateUrl(String email, GravatarRating rating, Integer size, String defaultImage) {
        if(!email)
            throw new RuntimeException("Email can not be empty or null")

        String emailHash = md5Hex(email.toLowerCase().trim())
        String params = generateUrlParameters(rating, size, defaultImage)
        GRAVATAR_URL + emailHash + params
    }

    private static generateUrlParameters(GravatarRating rating, Integer size, String defaultImage) {
        List<String> params = []

        params.add("s=" + (size ?: DEFAULT_SIZE) as String)

        String ratingCode = rating?.code ?: DEFAULT_RATING.code

        params.add("r=" + ratingCode)

        if (defaultImage)
            params.add("d=" + defaultImage)

        params.isEmpty() ? "" : "?" + StringUtils.join(params.iterator(), "&")
    }

    /**
     * Source taken from https://en.gravatar.com/site/implement/images/java/
     */
    private static String md5Hex (String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5")
            return hex (md.digest(message.getBytes("CP1252")))
        } catch (Exception e){
            log.error(e.message)
        }
        return null
    }

    private static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer()
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i]
                    & 0xFF) | 0x100).substring(1,3))
        }
        sb.toString()
    }
}
