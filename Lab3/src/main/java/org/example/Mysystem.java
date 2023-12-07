package org.example;
import java.time.LocalDate;
import java.util.List;
public class Mysystem implements InterSystem{
    String name;
    int InitialChatbotCodeLink;
    List<org.example.Chatbot> chatbots;

    String chatHistory;

    String userLogin;

    List<org.example.User> usuariosRegistrados;

    LocalDate fecha = LocalDate.now();


    public Mysystem(String name, int initialChatbotCodeLink, List<org.example.Chatbot> chatbots, String chatHistory, String userLogin, List<org.example.User> usuariosRegistrados) {
        this.name = name;
        InitialChatbotCodeLink = initialChatbotCodeLink;
        this.chatbots = chatbots;
        this.chatHistory = chatHistory;
        this.userLogin = userLogin;
        this.usuariosRegistrados = usuariosRegistrados;
    }

    public void systemAddChatbot(org.example.Chatbot chatbot){
        this.chatbots.add(chatbot);
    }
    public void systemAddUser(org.example.User usuario){
        this.usuariosRegistrados.add(usuario);
    }

    public void systemLogin(String nameUser){
        this.userLogin = nameUser;
    }

    public void systemLogout(){
        this.userLogin = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInitialChatbotCodeLink() {
        return InitialChatbotCodeLink;
    }

    public void setInitialChatbotCodeLink(int initialChatbotCodeLink) {
        InitialChatbotCodeLink = initialChatbotCodeLink;
    }

    public List<org.example.Chatbot> getChatbots() {
        return chatbots;
    }

    public void setChatbots(List<org.example.Chatbot> chatbots) {
        this.chatbots = chatbots;
    }

    public String getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(String chatHistory) {
        this.chatHistory = chatHistory;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public List<org.example.User> getUsuariosRegistrados() {
        return usuariosRegistrados;
    }

    public void setUsuariosRegistrados(List<org.example.User> usuariosRegistrados) {
        this.usuariosRegistrados = usuariosRegistrados;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "MySystem{" +
                "name='" + name + '\'' +
                ", InitialChatbotCodeLink=" + InitialChatbotCodeLink +
                ", \nchatbots=" + chatbots +
                ", chatHistory='" + chatHistory + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", usuariosRegistrados=" + usuariosRegistrados +
                ", fecha=" + fecha +
                '}';
    }
}
