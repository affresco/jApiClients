package PubSub;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Subscriber {

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SomeEventMessage event) {
        System.out.println("THIS IS MY EVENT: " + event.toString());
    };

    public Subscriber(){
        EventBus.getDefault().register(this);
    }


}
