package ictgradschool.web.jdbc.ex03;

public class RoleInfo {

    private String filmTitle;
    private String actorFirstName;
    private String actorLastName;
    private String roleName;

    public RoleInfo(String filmTitle, String actorFirstName, String actorLastName, String roleName) {
        this.filmTitle = filmTitle;
        this.actorFirstName = actorFirstName;
        this.actorLastName = actorLastName;
        this.roleName = roleName;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public String getActorFirstName() {
        return actorFirstName;
    }

    public String getActorLastName() {
        return actorLastName;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public String toString() {
        return actorFirstName + " " + actorLastName + " appears in '" + filmTitle + "' as: " + roleName;
    }
}
