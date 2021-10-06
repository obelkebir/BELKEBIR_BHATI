public class Response {

    public static String greetingResponse(){
        String res = 
        "HTTP/1.1 200 OK\n"+        
        "Content-length: 38\n"+
        "Content-type: text/html\n"+
        "Connection: Closed\n"+
        "\n"+
        "<h1>Hello<h1>\n"+
        "\r\n\r\n";
        return res;
    }


}