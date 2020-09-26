import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleReader {

    JDBSConnector connector;



    public ConsoleReader (){
         connector = null;
        try {
            connector = new JDBSConnector();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connector.createConnect();

    }



    public void read (){
        Scanner sc = new Scanner(System.in);
        String str;
        Command com;
        while (true){

            str = sc.nextLine();
            com = parse(str);

            if (!com.getErr().equals("noerr")){
                System.out.println(com.getErr());
                continue;
            }

            if (com.getCommand().equals("add")){
                connector.addUser(com.getUser());
            }

            if (com.getCommand().equals("show")){
                for (User user :connector.show())
                    System.out.println(user);
            }


            if (com.getCommand().equals("deletebyid")){
                connector.deleteByID(com.getUser().getId());
            }

            if (com.getCommand().equals("send")){
                HibernateSessionCreator.saveMessage(com.getMessage());
            }
            if (com.getCommand().equals("showmessagefrom")){
                for (Message message : HibernateSessionCreator.getMessagesFrom(com.getMessage().getFrom())){
                    System.out.println(message);
                }
            }
            if (com.getCommand().equals("showmessageto")){
                for (Message message : HibernateSessionCreator.getMessagesFrom(com.getMessage().getTo())){
                    System.out.println(message);
                }
            }

        }

    }





    public Command parse (String str ) {


        Command com = new Command();
        String[] strM = str.split(" ");

        if (strM[0].equals("add") || strM[0].equals("deletebyid") || strM[0].equals("changerolebyid" ) || strM[0].equals("show") || strM[0].equals("send") || strM[0].equals("showmessagefrom") || strM[0].equals("showmessageto"))  {
            com.setCommand(strM[0]);
        }
        else {
            com.setErr("ERROR: " + strM[0]  + " НЕ ЯВЛЯЕТСЯ КОМАНДОЙ");
        }

        if (strM[0].equals("send")){
            try {
                com.setMessage(new Message(Integer.parseInt(strM[1]), Integer.parseInt(strM[2]), strM[3]));
            }
            catch (ArrayIndexOutOfBoundsException ex){
                com.setErr("ERROR: для комнанды send нехободим аргумент в виде: send from to text ");
            }
        }
        if (strM[0].equals("showmessagefrom")){
            try {
                Message m = new Message();
                m.setFrom(Integer.parseInt(strM[1]));
                com.setMessage(m);
            }
            catch (ArrayIndexOutOfBoundsException ex){
                com.setErr("ERROR: для комнанды showmessagefrom нехободим аргумент в виде: showmessagefrom from ");
            }
        }
        if (strM[0].equals("showmessageto")){
            try {
                Message m = new Message();
                m.setTo(Integer.parseInt(strM[1]));
                com.setMessage(m);
            }
            catch (ArrayIndexOutOfBoundsException ex){
                com.setErr("ERROR: для комнанды showmessageto нехободим аргумент в виде: showmessageto to ");
            }
        }


        if (strM[0].equals("add")){
            try {
                com.setUser(new User(strM[1], strM[2], strM[3]));
            }
            catch (ArrayIndexOutOfBoundsException ex){
                com.setErr("ERROR: для комнанды add нехободим аргумент в виде: add name pass role ");
            }
        }

        if (strM[0].equals("deletebyid")){
            try {
                com.getUser().setId(Integer.parseInt(strM[1]));
            }
            catch (ArrayIndexOutOfBoundsException ex){
                com.setErr("ERROR: для комнанды deletebyid нехободим аргумент в виде: deletebyid id  ");
            }
        }

        if (strM[0].equals("changerolebyid")){
            try {
                com.getUser().setId(Integer.parseInt(strM[1]));
                com.getUser().setRole(strM[2]);
            }
            catch (ArrayIndexOutOfBoundsException ex){
                com.setErr("ERROR: для комнанды changerolebyid нехободим аргумент в виде: changerolebyid id role  ");
            }
        }
        return com;
    }








    public static class Command{
               private String command;
        private  User user = new User();
        private  String err = "noerr";
        private    Message message = new Message();
        public String getErr() {
            return err;
        }

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        public void setErr(String err) {
            this.err = err;
        }

        public String getCommand() {
            return command;
        }

        public void setCommand(String command) {
            this.command = command;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User arg) {
            this.user = arg;
        }

        public Command(String command, User arg , String err) {
            this.command = command;
            this.user = arg;
            this.err = err;
        }
        public Command() {

        }
    }
}
