package ictgradschool.web.jdbc.examples;

/**
 * A Plain Old Java Object (POJO) representing a Lecturer.
 * <p>
 * The unidb_lecturers table in the database (see examples-init.sql) can be used to persist objects of this type.
 */
public class Lecturer {

    /**
     * The lecturer's unique id. This field is an {@link Integer} rather than int, so it can be null. If the value is
     * null, this means that we haven't yet saved this lecturer to the database, and we expect the database to generate
     * the staffNo for us (using AUTO_INCREMENT).
     *
     * @see <a href="https://www.w3schools.com/sql/sql_autoincrement.asp">SQL AUTO_INCREMENT feature</a>
     */
    private Integer staffNo;

    private String firstName;

    private String lastName;

    private String office;

    /**
     * Creates a new {@link Lecturer}.
     */
    public Lecturer() {
    }

    /**
     * Creates a new {@link Lecturer}.
     *
     * @param staffNo   the lecturer's unique id.
     * @param firstName the lecturer's first name.
     * @param lastName  the lecturer's last name.
     * @param office    the lecturer's office code.
     */
    public Lecturer(Integer staffNo, String firstName, String lastName, String office) {
        this.staffNo = staffNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.office = office;
    }

    /**
     * Creates a new {@link Lecturer}.
     *
     * @param firstName the lecturer's first name.
     * @param lastName  the lecturer's last name.
     * @param office    the lecturer's office code.
     */
    public Lecturer(String firstName, String lastName, String office) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.office = office;
    }

    /// Getters and setters below ///

    public Integer getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(Integer staffNo) {
        this.staffNo = staffNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "staffNo=" + staffNo +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", office='" + office + '\'' +
                '}';
    }
}
