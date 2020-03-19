package se228.richard.ebookstore;

import java.security.Permission;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EncodeClassPermission extends Permission {

    public EncodeClassPermission(String path, String todo) {
        super(path);
        action = todo;
    }

    public String getActions() {
        return action;
    }

    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (!getClass().equals(other.getClass()))
            return false;
        EncodeClassPermission encodeClassPermission = (EncodeClassPermission) other;
        if (!action.equals(encodeClassPermission.action))
            return false;
        if (action.equals("allow"))
            return getName().equals(encodeClassPermission.getName());
        else if (action.equals("deny"))
            return blockedPathSet().equals(encodeClassPermission.blockedPathSet());
        else
            return false;
    }

    public int hashCode() {
        return getName().hashCode() + action.hashCode();
    }

    public boolean implies(Permission other) {
        if (!(other instanceof EncodeClassPermission))
            return false;
        EncodeClassPermission encodeClassPermission = (EncodeClassPermission) other;
        if (action.equals("allow")) {
            return encodeClassPermission.action.equals("allow") &&
                    getName().indexOf(encodeClassPermission.getName()) >= 0;
        }
        else if (action.equals("deny")) {
            if (encodeClassPermission.action.equals("deny"))
                return encodeClassPermission.blockedPathSet().containsAll(blockedPathSet());
            else if (encodeClassPermission.action.equals("allow")) {
                for (String badWord : blockedPathSet())
                    if (encodeClassPermission.getName().indexOf(badWord) >= 0)
                        return false;
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    public Set<String> blockedPathSet() {
        Set<String> set = new HashSet<String>();
        set.addAll(Arrays.asList(getName().split(";")));
        return set;
    }

    private String action;

}
