package task4;

public class test {

    public static void main(String[] args) {
        SocialNetworkControl soc_net = new SocialNetworkControl();

        soc_net.addPerson("Bob","Marley");
        soc_net.addFoto(15,"my foto","http://random.pic.com/pic.jpg");
        soc_net.addCommentToEntity(15,19,"my comment");

        soc_net.like(15,19);
        soc_net.countLikesOfEntity(19);
        soc_net.listWhoLikeTheEntity(19);
    }
}
