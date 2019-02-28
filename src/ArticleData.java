public class ArticleData {

    public static String articleTitle;

    public static String articleText;

    public static String articleAuthor;

    public static String articleSource;

    public ArticleData() {


    }



    public static void main(String[] args) {

        // Récupération des données

        String titre = "Titre",
                text = "Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme assembla ensemble des morceaux de texte pour réaliser un livre spécimen de polices de texte. Il n'a pas fait que survivre cinq siècles, mais s'est aussi adapté à la bureautique informatique, sans que son contenu n'en soit modifié. Il a été popularisé dans les années 1960 grâce à la vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus récemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker.\n",
                author = "J.K. Rowling",
                source = "New York Times";

        // Encapsulation des données

        articleTitle = titre;

        articleText = text;

        articleAuthor = author;

        articleSource = source;

        // Affichage des données

        ArticleView view = new ArticleView();

        view.displayArticle(articleTitle, articleText, articleAuthor, articleSource);

    }
}
