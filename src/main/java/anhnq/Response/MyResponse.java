package anhnq.Response;


import anhnq.Model.Notes_Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyResponse {

    String message;
    Object data;

    public MyResponse(List<Notes_Model> notes) {
    }

    public static MyResponse success(Object data)
    {
        return new MyResponse("success", data);
    }

    public static MyResponse fail(Object data)
    {
        return new MyResponse("fail", data);
    }


}
