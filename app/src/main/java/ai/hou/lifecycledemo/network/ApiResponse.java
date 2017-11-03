package ai.hou.lifecycledemo.network;

/**
 * @author zhaoxl
 * @since 2017/11/1
 */

public class ApiResponse<RequestType> {

    public String errorMessage;
    public RequestType body;

    public boolean isSuccessful() {
        return false;
    }
}
