package security;

import entity.User;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserPrincipal implements Principal {

  private int userId;
  private String username;
  private List<String> roles = new ArrayList<>();

  /* Create a UserPrincipal, given the Entity class User*/
  public UserPrincipal(User user) {
    this.userId = user.getUserId();
    this.username = user.getUserName();
    this.roles = user.getRolesAsStrings();
  }

  public UserPrincipal(int userId, String username, String... roles) {
    super();
    this.userId = userId;
    this.username = username;
    this.roles = Arrays.asList(roles);
  }

    public int getUserId() {
        return userId;
    }

  @Override
  public String getName() {
    return username;
  }

  public boolean isUserInRole(String role) {
    return this.roles.contains(role);
  }
}
