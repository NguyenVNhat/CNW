package cnw.utils.viewdto;

import java.util.HashMap;
import java.util.Map;

public class NotificationDto {
    Map<NotificationType, String> notifications = new HashMap<>();
    public void addNotification(NotificationType type, String message) {
        notifications.put(type, message);
    }
}
