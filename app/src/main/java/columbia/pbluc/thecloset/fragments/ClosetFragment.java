package columbia.pbluc.thecloset.fragments;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import columbia.pbluc.thecloset.R;

public class ClosetFragment extends Fragment {

  private String currentCategory;

  private ImageButton ibAddClosetItem;
  private ImageButton ibBackCategory;
  private Button btnTopsCategory;
  private Button btnBottomsCategory;
  private Button btnOnePiecesCategory;
  private Button btnDressesCategory;
  private Button btnShoesCategory;
  private Button btnUnderwearCategory;
  private Button btnBagsCategory;
  private Button btnHatsCategory;
  private Button btnOuterwearCategory;
  private Button btnGlovesCategory;
  private Button btnScarvesCategory;
  private Button btnTiesCategory;
  private Button btnSwimsuitsCategory;
  private Button btnBrasCategory;
  private LinearLayout linearLayoutClosetCategories;
  private LinearLayout linearLayoutTopsSubcategories;
  private LinearLayout linearLayoutBottomsSubcategories;


  public ClosetFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_closet, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    currentCategory = getResources().getString(R.string.categories_all);;

    ibAddClosetItem = view.findViewById(R.id.imageButtonAddClosetItem);
    ibBackCategory = view.findViewById(R.id.imageButtonBackCategory);
    btnTopsCategory = view.findViewById(R.id.buttonTopsCategory);
    btnBottomsCategory = view.findViewById(R.id.buttonBottomsCategory);
    btnOnePiecesCategory = view.findViewById(R.id.buttonOnePiecesCategory);
    btnDressesCategory = view.findViewById(R.id.buttonDressesCategory);
    btnShoesCategory = view.findViewById(R.id.buttonShoesCategory);
    btnUnderwearCategory = view.findViewById(R.id.buttonUnderwearCategory);
    btnBagsCategory = view.findViewById(R.id.buttonBagsCategory);
    btnHatsCategory = view.findViewById(R.id.buttonHatsCategory);
    btnOuterwearCategory = view.findViewById(R.id.buttonOuterwearCategory);
    btnGlovesCategory = view.findViewById(R.id.buttonGlovesCategory);
    btnScarvesCategory = view.findViewById(R.id.buttonScarvesCategory);
    btnTiesCategory = view.findViewById(R.id.buttonTiesCategory);
    btnSwimsuitsCategory = view.findViewById(R.id.buttonSwimsuitsCategory);
    btnBrasCategory = view.findViewById(R.id.buttonBrasCategory);
    linearLayoutClosetCategories = view.findViewById(R.id.linearLayoutClosetCategories);
    linearLayoutTopsSubcategories = view.findViewById(R.id.linearLayoutTopsSubcategories);
    linearLayoutBottomsSubcategories = view.findViewById(R.id.linearLayoutBottomsSubcategories);

    ibAddClosetItem.setOnClickListener(v -> {
      // TODO: Open up gallery to choose image
    });

    ibBackCategory.setOnClickListener(v -> {
      linearLayoutTopsSubcategories.setVisibility(View.GONE);
      linearLayoutBottomsSubcategories.setVisibility(View.GONE);
      linearLayoutClosetCategories.setVisibility(View.VISIBLE);

      ibBackCategory.setVisibility(View.GONE);

      currentCategory = getResources().getString(R.string.categories_all);
    });

    btnBottomsCategory.setOnClickListener(v -> {
      linearLayoutClosetCategories.setVisibility(View.GONE);
      linearLayoutTopsSubcategories.setVisibility(View.GONE);
      linearLayoutBottomsSubcategories.setVisibility(View.VISIBLE);

      ibBackCategory.setVisibility(View.VISIBLE);

      currentCategory = getResources().getString(R.string.categories_bottoms);
    });

    btnTopsCategory.setOnClickListener((v -> {
      linearLayoutClosetCategories.setVisibility(View.GONE);
      linearLayoutBottomsSubcategories.setVisibility(View.GONE);
      linearLayoutTopsSubcategories.setVisibility(View.VISIBLE);

      ibBackCategory.setVisibility(View.VISIBLE);

      currentCategory = getResources().getString(R.string.categories_tops);
    }));
  }
}