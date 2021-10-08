package tv.fengmang.ccpush;

public class Response<T> {
    public final static int SUCCESS = 0;
    public final static int FAILED = -1;

    public Response(int status) {
        this.status = status;
    }

    private int status;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public static Response success() {
        return new Response(SUCCESS);
    }

    public static Response failed() {
        return new Response(FAILED);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
