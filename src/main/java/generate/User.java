package generate;

import java.io.Serializable;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    private Long id;

    private String accountId;

    private String name;

    private String token;

    private Long gmtCreate;

    private Long gmtModified;

    private String password;

    private String bio;

    private String iconUrl;

    private String mailbox;

    private static final long serialVersionUID = 1L;
}