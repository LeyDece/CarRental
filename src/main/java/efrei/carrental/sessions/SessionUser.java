package efrei.carrental.sessions;

import java.io.IOException;
import java.net.InetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Inserts the session details into the session for every request. Some users may prefer
 * to insert session details only after authentication. This is fine, but it may be
 * valuable to the most up to date information so that if someone stole the user's session
 * id it can be observed.
 *
 * @author Rob Winch
 *
 */
// tag::class[]
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 101)
public class SessionUser extends OncePerRequestFilter {

    static final String UNKNOWN = "Unknown";


    @Autowired
    public SessionUser() {
    }

    // tag::dofilterinternal[]
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(request, response);

        HttpSession session = request.getSession(false);
        if (session != null) {
            var details = new SessionDetails();
            details.setUserId("user_test");
            session.setAttribute("SESSION_DETAILS", details);
        }
    }
    // end::dofilterinternal[]

}