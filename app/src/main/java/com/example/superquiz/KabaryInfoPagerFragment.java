package com.example.superquiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class KabaryInfoPagerFragment extends Fragment {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private Button nextButton;
    private Button skipButton;
    private TextView pageIndicator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kabary_info_pager, container, false);

        viewPager = view.findViewById(R.id.view_pager);
        tabLayout = view.findViewById(R.id.tab_layout);
        nextButton = view.findViewById(R.id.next_page_button);
        skipButton = view.findViewById(R.id.skip_button);
        pageIndicator = view.findViewById(R.id.page_indicator);

        // Adapter pour ViewPager2
        InfoPagerAdapter adapter = new InfoPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Lier TabLayout avec ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            // Optionnel : personnaliser les tabs
        }).attach();

        // Indicateur de page
        updatePageIndicator(0);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                updatePageIndicator(position);

                if (position == 3) { // Dernière page
                    nextButton.setText("Commencer le Quiz");
                } else {
                    nextButton.setText("Suivant");
                }
            }
        });

        // Bouton suivant
        nextButton.setOnClickListener(v -> {
            int currentItem = viewPager.getCurrentItem();
            if (currentItem < 3) {
                viewPager.setCurrentItem(currentItem + 1);
            } else {
                // Aller au quiz
                goToWelcome();
            }
        });

        // Bouton passer
        skipButton.setOnClickListener(v -> goToWelcome());

        return view;
    }

    private void updatePageIndicator(int position) {
        pageIndicator.setText((position + 1) + " / 4");
    }

    private void goToWelcome() {
        getParentFragmentManager().popBackStack();
    }

    // Adapter pour les pages d'information
    private static class InfoPagerAdapter extends FragmentStateAdapter {

        public InfoPagerAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return KabaryInfoPageFragment.newInstance(position);
        }

        @Override
        public int getItemCount() {
            return 4; // 4 pages d'information
        }
    }
}