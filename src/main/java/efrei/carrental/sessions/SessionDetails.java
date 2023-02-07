package efrei.carrental.sessions;

import lombok.Data;

import java.io.Serializable;

/**
 * An example of how users can provide details about their session.
 *
 * @author Rob Winch
 * @see SessionDetailsFilter
 */
// tag::class[]
@Data
public class SessionDetails implements Serializable {

    String userId;

}