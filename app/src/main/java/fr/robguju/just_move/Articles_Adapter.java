package fr.robguju.just_move;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;


public class Articles_Adapter extends BaseAdapter {

    //attributs
    private Context context;
    private List<Article> articles_list;
    private LayoutInflater inflater;

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
        view = inflater.inflate(R.layout.adapter_list, null);
        //on se place à un article
        Article article_courant = getItem(i);

        //pour chaque article, on récupère  le titre, le tag et le resumé
        String i_title = article_courant.getTitle();
        String i_tag = article_courant.getTag();
        String i_resume = article_courant.getResume();

        //on cherche l'image dans drawable, on le le lie au XML et on l'affiche
        String fav_ressource_img = "ic_non_favori";
        int i_favori_ressource = context.getResources().getIdentifier(fav_ressource_img, "drawable", context.getPackageName());
        ImageView i_fav_view = view.findViewById(R.id.favori_item);
        i_fav_view.setImageResource(i_favori_ressource);

        //on cherche l'image par le nom dans "drawable", on le lie avec le XML et on l'affiche
        String ressource_img = "article_" + i_tag + "_img";
        int i_ressource = context.getResources().getIdentifier(ressource_img, "drawable", context.getPackageName());
        ImageView i_img_view = view.findViewById(R.id.image_item);
        i_img_view.setImageResource(i_ressource);

        //on lie le titre avec le XML et on l'affiche
        TextView i_title_view = view.findViewById(R.id.titre_item);
        i_title_view.setText(i_title);

        //on lie le resume avec le XML et on l'affiche
        TextView i_resume_view = view.findViewById(R.id.resume_item);
        i_resume_view.setText(i_resume);
        if(article_courant.fav){
            i_fav_view.setImageResource(R.drawable.ic_favori);
        }
        else {
            i_fav_view.setImageResource(R.drawable.ic_non_favori);
        }



        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();


            }
        });
        return view;
    }
}

