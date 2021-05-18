package fr.robguju.just_move;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;


public class Articles_Adapter extends BaseAdapter{

    //attributs
    public Context context;
    private List<Article> articles_list;
    private LayoutInflater inflater;
    Dialog popup;

    //constructeur
    public Articles_Adapter(Context context, List<Article> articles_list) {
        this.context = context;
        this.articles_list = articles_list;
        this.inflater = LayoutInflater.from(context);
    }

    //trouver le nombre d'articles
    @Override
    public int getCount() {
        return articles_list.size();
    }

    //trouver la position de l'article
    @Override
    public Article getItem(int position) {
        return articles_list.get(position);
    }

    //pas besoin
    @Override
    public long getItemId(int position) {
        return 0;
    }

    //afficher tous les articles
    @Override
    public View getView(int i, View view, ViewGroup parent) {
        //creation de la vue d'un article
        view = inflater.inflate(R.layout.adapter_list, null);
        //on se place à un article
        Article article_courant = getItem(i);

        //pour chaque article, on récupère  le titre, le tag et le resumé
        String i_title = article_courant.getTitle();
        String i_tag = article_courant.getTag();
        String i_resume = article_courant.getResume();
        boolean i_fav = article_courant.isFav();
        String i_text = article_courant.getText();

        //on cherche l'image dans drawable, on le le lie au XML et on l'affiche (FAVORI)
        String fav_img = "ic_favori";
        int i_favori = context.getResources().getIdentifier(fav_img, "drawable", context.getPackageName());
        String non_fav_img = "ic_non_favori";
        int i_non_favori = context.getResources().getIdentifier(non_fav_img, "drawable", context.getPackageName());
        //on cherche l'image par le nom dans "drawable", on le lie avec le XML et on l'affiche (IMAGE)
        String ressource_img = "article_" + i_tag + "_img";
        int i_ressource = context.getResources().getIdentifier(ressource_img, "drawable", context.getPackageName());
        //on cherche l'image par le nom dans "drawable", on le lie avec le XML et on l'affiche (CROIX)
        String croix = "ic_croix";
        int i_croix = context.getResources().getIdentifier(croix, "drawable", context.getPackageName());

        //on lie le XML avec les variable Java de articles de la page principale
        TextView i_title_view = view.findViewById(R.id.titre_item);
        TextView i_resume_view = view.findViewById(R.id.resume_item);
        ImageView i_fav_view = view.findViewById(R.id.favori_item);
        ImageView i_img_view = view.findViewById(R.id.image_item);

        //on attribue à la variable les données de l'article correspondant
        i_title_view.setText(i_title);
        i_resume_view.setText(i_resume);
        i_img_view.setImageResource(i_ressource);
        if (i_fav){
            i_fav_view.setImageResource(i_favori);
        }
        else {
            i_fav_view.setImageResource(i_non_favori);
        }



        //Gestion du popup
        popup = new Dialog(context);
        popup.setContentView(R.layout.activity_pop_up);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on lie le XML avec les variables Java de la popup
                TextView titre_dialog = popup.findViewById(R.id.popup_titre_default);
                TextView resume_dialog = popup.findViewById(R.id.popup_resume_default);
                TextView text_dialog = popup.findViewById(R.id.popup_texte_default);
                ImageView img_dialog = popup.findViewById(R.id.popup_img_default);
                ImageView croix_dialog = popup.findViewById(R.id.popup_croix);
                ImageView fav_dialog = popup.findViewById(R.id.popup_favori_default);
                titre_dialog.setText(i_title);
                resume_dialog.setText(i_resume);
                text_dialog.setText(i_text);
                img_dialog.setImageResource(i_ressource);
                croix_dialog.setImageResource(i_croix);
                popup.show();

                //gestion de la fermeture du popup
                croix_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.dismiss();
                    }
                });

                //gestion du favori du popup
                fav_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(article_courant.fav){
                            i_fav_view.setImageResource(R.drawable.ic_non_favori);
                            fav_dialog.setImageResource(R.drawable.ic_non_favori);
                            article_courant.fav = false;
                        }
                        else {
                            i_fav_view.setImageResource(R.drawable.ic_favori);
                            fav_dialog.setImageResource(R.drawable.ic_favori);
                            article_courant.fav = true;
                        }
                    }
                });

                //gestion du favori de la page principale
                i_fav_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(article_courant.fav){
                            i_fav_view.setImageResource(R.drawable.ic_non_favori);
                            fav_dialog.setImageResource(R.drawable.ic_non_favori);
                            article_courant.fav = false;
                        }
                        else {
                            i_fav_view.setImageResource(R.drawable.ic_favori);
                            fav_dialog.setImageResource(R.drawable.ic_favori);
                            article_courant.fav = true;
                        }
                    }
                });

            }
        });
        return view;
    }
}

