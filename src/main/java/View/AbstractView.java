package View;

import Model.Admin;
import Model.UserAbstract;

import java.util.List;

public abstract class AbstractView {
    public abstract void  print(String message);
    public abstract void print(List<UserAbstract> PersonsList);

}
