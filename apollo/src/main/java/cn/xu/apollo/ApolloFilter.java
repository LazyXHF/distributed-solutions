package cn.xu.apollo;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.stereotype.Component;

/**
 * @author ~许小桀
 * @date 2019/11/2 19:59
 */
@Component
public class ApolloFilter {

    // config change listener for namespace application
    @ApolloConfigChangeListener("application")
    private void anotherOnChange(ConfigChangeEvent changeEvent) {

        ConfigChange change = changeEvent.getChange("zhangsan_conf");
        System.out.println(String.format("Found change - key: %s, oldValue: %s," + "newValue: %s, changeType: %s",
                change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
    }

}