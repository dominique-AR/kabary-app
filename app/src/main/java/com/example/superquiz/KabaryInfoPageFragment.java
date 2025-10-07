package com.example.superquiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class KabaryInfoPageFragment extends Fragment {

    private static final String ARG_PAGE_NUMBER = "page_number";

    public static KabaryInfoPageFragment newInstance(int pageNumber) {
        KabaryInfoPageFragment fragment = new KabaryInfoPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kabary_info_page, container, false);

        TextView titleText = view.findViewById(R.id.info_title);
        TextView contentText = view.findViewById(R.id.info_content);
        ImageView iconImage = view.findViewById(R.id.info_icon);

        int pageNumber = getArguments() != null ? getArguments().getInt(ARG_PAGE_NUMBER) : 0;

        // Configurer le contenu selon la page
        switch (pageNumber) {
            case 0: // Page 1: Définition
                titleText.setText("Alors, c'est quoi le kabary malagasy ?");
                contentText.setText("🗣️ Imagine un discours cérémoniel... mais pas n'importe lequel ! Chaque mot est choisi avec soin, chaque phrase a du rythme, et surtout, ça se fait devant tout le monde, avec beaucoup de respect et de symboles.\n\n" +
                        "📜 Origines :\n" +
                        "Le kabary, c'est un héritage oral qui remonte très loin, dès le IXe siècle selon certaines traditions. Andriamandravolona l'art a vraiment pris forme. À l'époque, il n'était réservé qu'aux rois, chefs de clan et élites. Le but ? Annoncer des décisions, résoudre des conflits et surtout souder la communauté.");
                iconImage.setImageResource(R.drawable.ic_speech);
                break;

            case 1: // Page 2: Points clés
                titleText.setText("🎯 Les points clés à connaître");
                contentText.setText("📋 Origines : art oratoire ancien, structuré dès le IXe siècle, institutionnalisé par les rois merina\n\n" +
                        "🎭 Caractéristiques : discours poétisé, avec proverbes (ohabolana), maximes, salutations codifiées\n\n" +
                        "👥 Orateurs : les mpikabary, figures de sagesse et d'éloquence\n\n" +
                        "🎪 Usages : mariages, funérailles, affaires foncières, cérémonies officielles, vie politique\n\n" +
                        "🌍 Évolution : autrefois réservé aux rois et anciens, aujourd'hui ouvert aux jeunes et aux femmes\n\n" +
                        "💡 Reconnaissance : inscrit en 2021 au patrimoine immatériel de l'humanité par l'UNESCO");
                iconImage.setImageResource(R.drawable.ic_checklist);
                break;

            case 2: // Page 3: Structure
                titleText.setText("👉 Caractéristiques :");
                contentText.setText("un kabary, ce n'est pas de l'improvisation.\n\n" +
                        "C'est une véritable partition orale 🎵 :\n\n" +
                        "1. Tari-dresaka - Introduction\n" +
                        "2. Alasora - Le dévoilement\n" +
                        "3. Azafady - La demande de pardon 🙏\n" +
                        "4. Falan-tany - Les excuses 🙇\n" +
                        "5. Hasina sy Arahaba - Sacralisation et salutations\n" +
                        "6. Firariantsoa - Les souhaits 🌟\n" +
                        "7. Ranarivo kabary - Le contenu 💬\n" +
                        "8. Fisaorana sy Famaranana - Remerciement et conclusion\n\n" +
                        "C'est l'union sociale et le respect mutuel qui sont célébrés.");
                iconImage.setImageResource(R.drawable.ic_structure);
                break;

            case 3: // Page 4: Astuce
                titleText.setText("💡 Astuce pour comprendre :");
                contentText.setText("Pense au kabary comme à une cérémonie de la parole : chaque étape est codée, chaque formule a un sens, et derrière tout cela...\n\n" +
                        "C'est l'union sociale et le respect mutuel qui sont célébrés.\n\n" +
                        "🎭 Le kabary, c'est:\n" +
                        "• L'art de parler avec sagesse\n" +
                        "• Le respect des traditions\n" +
                        "• La transmission des valeurs\n" +
                        "• L'union de la communauté\n\n" +
                        "Maintenant que tu connais l'essentiel, es-tu prêt(e) à tester tes connaissances ? 🚀");
                iconImage.setImageResource(R.drawable.ic_lightbulb);
                break;
        }

        return view;
    }
}