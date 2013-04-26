package user;

import org.apache.thrift.TException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: skplanet
 * Date: 13. 4. 26.
 * Time: 오후 3:00
 * To change this template use File | Settings | File Templates.
 */
public class UserItemServiceImpl implements UserItemService.Iface {

    private UserItemList list = new UserItemList();



    @Override
    public UserItemList getUserItemList(String id) throws TException {
        UserItem userItem = new UserItem();
        userItem.setId("jaejin");
        userItem.setItem("hoppin");

        list.addToItemList(userItem);
        userItem = new UserItem();
        userItem.setId("jaejin");
        userItem.setItem("tving");

        System.out.println("getUserList");
        list.addToItemList(userItem);
        return list;
    }
}
