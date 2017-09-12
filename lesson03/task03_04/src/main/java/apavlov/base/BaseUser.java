package apavlov.base;

import apavlov.models.Account;
import apavlov.models.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * The class BaseUser - base work with users and user`s acoounts.
 *
 * @author Pavlov Artem
 * @since 12.09.2017
 */
public class BaseUser {
    /**
     * The collections type HashMap.
     */
    private Map<User, List<Account>> mapUsers = new HashMap<>();

    /**
     * The method return length collection.
     *
     * @return - length collection;
     */
    public int getSize() {
        return mapUsers.size();
    }

    /**
     * The method return length accounts to user.
     *
     * @param user - user;
     * @return - length accounts user`s;
     */
    public int getAccountSize(User user) {
        return user != null ? this.mapUsers.get(user).size() : 0;
    }

    /**
     * The method add new user to collection.
     *
     * @param user - user;
     */
    public void addUser(User user) {
        if (user != null) {
            this.mapUsers.put(user, new LinkedList<>());
        }
    }

    /**
     * The method remove user`s with base.
     *
     * @param user - user;
     */
    public void deleteUser(User user) {
        if (user != null) {
            this.mapUsers.remove(user);
        }
    }

    /**
     * The method add new account to user.
     *
     * @param user    - user;
     * @param account - account user`s;
     */
    public void addAccountToUser(User user, Account account) {
        boolean result = false;
        if (user != null && account != null) {
            List<Account> list = getUserAccounts(user);
            if (list != null && list.size() > 0) {
                for (Account ac : list) {
                    if (account.getRequisites() == ac.getRequisites()) {
                        result = true;
                    }
                }
            }
        }
        if (!result) {
            this.mapUsers.get(user).add(account);
        }
    }

    /**
     * The method delete account to user`s.
     *
     * @param user    - user;
     * @param account - account user`s;
     */
    public void deleteAccountFromUser(User user, Account account) {
        if (user != null && account != null) {
            this.mapUsers.get(user).remove(account);
        }
    }

    /**
     * The method return link list accounts to user.
     *
     * @param user - user;
     * @return - link list;
     */
    public List<Account> getUserAccounts(User user) {
        return this.mapUsers.get(user);
    }

    /**
     * The method transfer money with account to other account.
     *
     * @param srcUser - from user;
     * @param srcAccount - from account;
     * @param dstUser - to user;
     * @param dstAccount - to account;
     * @param amount - count money;
     * @return - false - is not transfer; true - is transfer;
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean result = false;
        if (srcUser != null && srcAccount != null && dstUser != null && dstAccount != null && amount > 0) {
            List<Account> srcLinkList = getUserAccounts(srcUser);
            List<Account> dstLinkList = getUserAccounts(dstUser);
            if (srcLinkList != null && dstLinkList != null) {
                if (srcLinkList.contains(srcAccount) && dstLinkList.contains(dstAccount) && amount <= srcAccount.getCountMoney()) {
                    Account linkAccountSrc = srcLinkList.get(srcLinkList.indexOf(srcAccount));
                    Account linkAccountDst = dstLinkList.get(dstLinkList.indexOf(dstAccount));
                    linkAccountDst.setCountMoney(linkAccountDst.getCountMoney() + amount);
                    linkAccountSrc.setCountMoney(linkAccountSrc.getCountMoney() - amount);
                    result = true;
                }
            }
        }
        return result;
    }
}
