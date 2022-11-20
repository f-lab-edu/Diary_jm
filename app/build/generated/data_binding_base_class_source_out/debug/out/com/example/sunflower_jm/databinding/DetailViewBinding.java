// Generated by view binder compiler. Do not edit!
package com.example.sunflower_jm.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.sunflower_jm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DetailViewBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView detailContent;

  @NonNull
  public final ImageView detailImage;

  @NonNull
  public final TextView detailTitle;

  @NonNull
  public final FloatingActionButton update;

  private DetailViewBinding(@NonNull ConstraintLayout rootView, @NonNull TextView detailContent,
      @NonNull ImageView detailImage, @NonNull TextView detailTitle,
      @NonNull FloatingActionButton update) {
    this.rootView = rootView;
    this.detailContent = detailContent;
    this.detailImage = detailImage;
    this.detailTitle = detailTitle;
    this.update = update;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DetailViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DetailViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.detail_view, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DetailViewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.detail_content;
      TextView detailContent = ViewBindings.findChildViewById(rootView, id);
      if (detailContent == null) {
        break missingId;
      }

      id = R.id.detail_image;
      ImageView detailImage = ViewBindings.findChildViewById(rootView, id);
      if (detailImage == null) {
        break missingId;
      }

      id = R.id.detail_title;
      TextView detailTitle = ViewBindings.findChildViewById(rootView, id);
      if (detailTitle == null) {
        break missingId;
      }

      id = R.id.update;
      FloatingActionButton update = ViewBindings.findChildViewById(rootView, id);
      if (update == null) {
        break missingId;
      }

      return new DetailViewBinding((ConstraintLayout) rootView, detailContent, detailImage,
          detailTitle, update);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
