package cnw.utils.servlet;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
@Getter
public class FlashMap {

    private final Map<String, String> targetRequestParams = new HashMap<>();
    public String toString() {
        return "FlashMap [attributes=" + super.toString() + ", targetRequestParams=" + this.targetRequestParams + "]";
    }
    public void addTargetRequestParams(String name, String value) {
        this.targetRequestParams.put(name, value);
    }
    public void clearTargetRequestParams() {
        this.targetRequestParams.clear();
    }
    public void putAllTargetRequestParams(Map<String, String> attributes) {
        this.targetRequestParams.putAll(attributes);
    }
}
