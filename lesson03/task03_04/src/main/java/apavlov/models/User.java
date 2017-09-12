package apavlov.models;

/**
 * The class User - model with field name and passport, for save data user.
 *
 * @author Pavlov Artem
 * @since 12.09.2017
 */
public class User {
    /**
     * The var - name user.
     */
    private String name;

    /**
     * The var - number passport user.
     */
    private String passport;

    /**
     * The getter for var name.
     *
     * @return - name user`s;
     */
    public String getName() {
        return name;
    }

    /**
     * The getter for var passport.
     *
     * @return - number passport user`s;
     */
    public String getPassport() {
        return passport;
    }

    /**
     * The constructor for class User.
     *
     * @param name     - name user`s;
     * @param passport - number passport user`s;
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    @Override
    public int hashCode() {
        int result = 13;
        char[] fullSymbols = String.format("[name-%s][passport-%s]", this.name, this.passport).toCharArray();
        for (int i = 0; i < fullSymbols.length; i++) {
            result += (i + 1) * (int) fullSymbols[i];
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = obj != null;
        if (result) {
            result = !(obj != this && getClass() == obj.getClass());
            if (!result) {
                User user = (User) obj;
                result = user.getName().equals(this.name) && user.getPassport().equals(this.passport);
            }
        }
        return result;
    }
}
