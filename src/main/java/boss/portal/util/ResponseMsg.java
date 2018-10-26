package boss.portal.util;

import java.io.Serializable;

/**
 * Created by Dell on 2018/10/25.
 */
public class ResponseMsg implements Serializable {

    public String returnCode;

    public String errorMessageEN;

    public ResponseMsg(String returnCode,String errorMessageEN)
    {
        this.returnCode=returnCode;
        this.errorMessageEN=errorMessageEN;
    }

}
