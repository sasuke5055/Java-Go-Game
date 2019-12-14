
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;

/**
 *
 * Ta klasa implementuje Socket server
 *
 */

public class Server {
        private static ServerSocket server;
        /*port socket serwer-a*/
        private static int port = 6666;

        public static String mes1="",mes2="";
        public static Thread client2Thread= new ClientReciveChatInfo();
        public static Thread client1Thread= new ClientReciveChatInfo();

        public static Thread client2GameThread= new ClientReciveGameInfo();
        public static Thread client1GameThread= new ClientReciveGameInfo();
        public static int a1=21;
        public static int b1=21;
        public static int a2=21;
        public static int b2=21;


        public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
                /**tworzenie socket serwer*/
                server = new ServerSocket(port);
                System.out.println("Stworzylem server");
                /*Tutaj daje wiadomosc klientom ktory byl pierwszy*/


                //******************************//
                //---- UNDER CONSTRUCKTION------//
                //******************************//

                /*Czekam na klienta 1*/
                System.out.println("Czekam na 1 gracza");
                /**Czekam na klienta 1*/
                Socket socket1 = server.accept();
                System.out.println("Gracz 1 dolaczyl do serwera");
                /**Daje klientowi 1 odpowiedz*/
                ObjectOutputStream oos1 = new ObjectOutputStream(socket1.getOutputStream());
                oos1.writeObject(true);

                /**Teraz biore wiadomosc od klienta 1 */
                ObjectInputStream ois1 = new ObjectInputStream(socket1.getInputStream());


                /*Czekam na klienta 2*/
                System.out.println("Czekam na 2 gracza");
                /**Czekam na klienta 2*/
                Socket socket2 = server.accept();
                System.out.println("Gracz 2 dolaczyl do serwera");

                /**Konwertuje na inta*/
                String a = (String) ois1.readObject();
                System.out.println("Dostalem waidomosc od 1 gracza: " + a);

                /**Daje klientowi 2 odpowiedz*/
                ObjectOutputStream oos2 = new ObjectOutputStream(socket2.getOutputStream());
                oos2.writeObject(false);

                /**Teraz biore wiadomosc od klienta 2 */
                ObjectInputStream ois2 = new ObjectInputStream(socket2.getInputStream());

                /**Konwertuje na inta*/
                String b = (String) ois2.readObject();
                System.out.println("Dostalem widomosc od 2 gracza: " + b);



                /**informuje klienta 1 ze wszyscy sa*/
                oos2.writeObject(true);

                /**informuje klienta 2 ze wszyscy sa*/
                oos1.writeObject(true);

                /******************************************/
                /*Otwieram strumien do gry miedzy graczami*/
                /******************************************/
                ServerGame(socket1,socket2, ois1,ois2,oos1,oos2);
         /*       while(true){


                        /*Teraz biore wiadomosc od klienta 1 */
                        //ObjectInputStream checker1 = new ObjectInputStream(socket1.getInputStream());

                        /**BIore wiadomosc od klij. 1 i konwertuje na inta*/
         /*               a1 = (int) ois1.readObject();
                        System.out.println("Dostalem widomosc od 1 gracza: " + a1);
                        /**BIore wiadomosc od klij. 1 i konwertuje na inta*/
         /*               b1 = (int) ois1.readObject();
                        System.out.println("Dostalem widomosc od 1 gracza: " + b1);

                        /**Sprawdzam czy to juz koniec naszej zabawy*/
        /*                if(a1==20 && a2==20 && b1==20 && b2==20)
                        {                        /*Teraz biore wiadomosc od klienta 2*/
                                //ObjectInputStream checker2 = new ObjectInputStream(socket2.getInputStream());

                                /**Konwertuje na inta*/
        /*                        a2 = (int) ois2.readObject();
                                System.out.println("Dostalem widomosc od 2 gracza: " + a2);
                                b2 = (int) ois2.readObject();
                                System.out.println("Dostalem widomosc od 2 gracza: " + b2);

                                System.out.println("daje odpow 2 graczowi");
                                /**Daje klientowi 2 odpowiedz*/
                                // ObjectOutputStream oos1a = new ObjectOutputStream(socket1.getOutputStream());
        /*                        oos2.writeObject(a1);
                                oos2.writeObject(b1);
                                break;
                        }

                        /**Teraz biore wiadomosc od klienta 2 i konwertuje na inta*/
         /*               a2 = (int) ois2.readObject();
                        System.out.println("Dostalem widomosc od 2 gracza: " + a2);
                        /**Teraz biore wiadomosc od klienta 2 i konwertuje na inta*/
         /*               b2 = (int) ois2.readObject();
                        System.out.println("Dostalem widomosc od 2 gracza: " + b2);

                        System.out.println("daje odpow 1 graczowi");
                        /**Daje klientowi 1 odpowiedz*/
         /*               oos1.writeObject(a2);
                        oos1.writeObject(b2);

                        System.out.println("daje odpow 2 graczowi");
                        /**Daje klientowi 2 odpowiedz*/
         /*               oos2.writeObject(a1);
                        oos2.writeObject(b1);

                        /**Sprawdzam czy to juz koniec naszej zabawy*/
         /*               if(a1==20 && a2==20 && b1==20 && b2==20)
                        {
                                System.out.println("A TERAZ JESTEM TU, ludzi tlum");
                                /**Konwertuje na inta*/
                                /*a1 = (int) ois1.readObject();
                                System.out.println("Dostalem widomosc od 1 gracza: " + a1);
                                b1 = (int) ois1.readObject();
                                System.out.println("Dostalem widomosc od 1 gracza: " + b1);
                                System.out.println("daje odpow 1 graczowi");
                                /**Daje klientowi 2 odpowiedz*/
                                // ObjectOutputStream oos1a = new ObjectOutputStream(socket1.getOutputStream());
                                /*oos1.writeObject(a2);
                                oos1.writeObject(b2);*/
        /*                        break;
                        }
                }

        */
                /******************************************/
                /*Otwieram czat do rozmowy miedzy graczami*/
                /******************************************/

                ServerChat(socket1,socket2, ois1,ois2,oos1,oos2);




                /**zamykam wszystkie zrodla*/
                oos2.close();
                ois1.close();
                oos1.close();
                oos2.close();
                socket2.close();
                socket1.close();

                System.out.println("Shutting down Socket server!!");
                /**zamykam ServerSocket object*/
                server.close();
        }
        public static void ServerGame(Socket socket1, Socket socket2, ObjectInputStream ois1, ObjectInputStream ois2, ObjectOutputStream oos1, ObjectOutputStream oos2) throws InterruptedException, IOException {
                ((ClientReciveGameInfo) client2GameThread).socket = socket2;
                ((ClientReciveGameInfo) client2GameThread).ois = ois2;
                ((ClientReciveGameInfo) client2GameThread).isBlack = false;
                client2GameThread.start();

                ((ClientReciveGameInfo) client1GameThread).socket = socket1;
                ((ClientReciveGameInfo) client1GameThread).ois = ois1;
                ((ClientReciveGameInfo) client1GameThread).isBlack = true;
                client1GameThread.start();

                while(true) {


                        System.out.println("Wysylam1 : " + a2+ b2);
                        /**Daje klientowi 1 odpowiedz*/
                        oos1.writeObject(a2);
                        oos1.writeObject(b2);

                        System.out.println("Wysylam2 : " + a1 + b1);
                        /**Daje klientowi 2 odpowiedz*/
                        oos2.writeObject(a1);
                        oos2.writeObject(b1);

                        if (a1 == 20 && a2 == 20 && b1 == 20 && b2 == 20) {
                                client2GameThread.interrupt();
                                client1GameThread.interrupt();
                                //ServerChat(socket1, socket2, ois1, ois2, oos1, oos2);
                                a1 = 21;
                                a2 = 21;
                                b1 = 21;
                                b2 = 21;
                                break;
                        }

                        sleep(100);
                }
        }
        public static void ServerChat(Socket socket1, Socket socket2, ObjectInputStream ois1, ObjectInputStream ois2, ObjectOutputStream oos1, ObjectOutputStream oos2) throws InterruptedException {
                ((ClientReciveChatInfo) client2Thread).socket=socket2;
                ((ClientReciveChatInfo) client2Thread).ois=ois2;
                ((ClientReciveChatInfo) client2Thread).isBlack=false;
                client2Thread.start();


                ((ClientReciveChatInfo) client1Thread).socket=socket1;
                ((ClientReciveChatInfo) client1Thread).ois=ois1;
                ((ClientReciveChatInfo) client1Thread).isBlack=true;
                client1Thread.start();

                /**Zmienne przechowujace wiadomosci 1 i 2 gracza*/

                while(true){

                        /**Daje klientowi 2 odpowiedz*/
                        try {
                                oos2.writeObject(mes1);
                                mes1 = "";
                        } catch (IOException e) {
                                e.printStackTrace();
                        }



                        /**Daje klientowi 1 odpowiedz*/
                        try {
                                oos1.writeObject(mes2);
                                mes2 = "";
                        } catch (IOException e) {
                                e.printStackTrace();
                        }

                        sleep(1000);

                        /**Sprawdzam czy doszlo do porozumienia miedzy graczami*/
                        if(mes1=="zacznij gre" ||  mes2=="zacznij gre") break;
                }
        }
}
class ClientReciveChatInfo extends Thread {
        public Socket socket;
        public ObjectInputStream ois;
        public ObjectOutputStream oos;
        boolean isBlack = true;
        public Server server;

        /**
         * Biore i konwertuje na String wiadomosc od 1 klienta
         */
        String mes;

        @Override
        public synchronized void run() {
                System.out.println("Jestem w watku");
                while(true){
                        try {
                                mes = (String) ois.readObject();
                                if(isBlack)server.mes1=mes;
                                else server.mes2=mes;
                        } catch (IOException e) {
                                e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                        }

                        System.out.println("Dostalem widomosc od 1 gracza: " + mes);
                }


                // System.out.println("Dostalem widomosc od 1 gracza: " + mes);
        }
}

class ClientReciveGameInfo extends Thread {
        public Socket socket;
        public ObjectInputStream ois;
        public ObjectOutputStream oos;
        boolean isBlack = true;
        public Server server;

        /**
         * Biore i konwertuje na int wiadomosc od klienta
         */
        int a;
        int b;

        @Override
        public synchronized void run() {
                System.out.println("Jestem w watku");
                while(true){
                        try {
                                /**BIore wiadomosc od klij. 1 i konwertuje na inta*/
                                a = (int) ois.readObject();
                                /**BIore wiadomosc od klij. 1 i konwertuje na inta*/
                                b = (int) ois.readObject();
                                System.out.println("Dostalem widomosc od  gracza: " + a + b);
                                if(isBlack){
                                        server.a1=a;
                                        server.b1=b;
                                }
                                else
                                {
                                        server.a2=a;
                                        server.b2=b;
                                }
                        } catch (IOException e) {
                                e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                        }
                }

        }
}