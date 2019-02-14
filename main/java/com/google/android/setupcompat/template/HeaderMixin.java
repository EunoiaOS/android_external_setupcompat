/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.setupcompat.template;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;
import com.google.android.setupcompat.R;
import com.google.android.setupcompat.internal.TemplateLayout;

/**
 * A {@link com.google.android.setupcompat.template.Mixin} for setting and getting the header text.
 */
public class HeaderMixin implements Mixin {

  private final TemplateLayout templateLayout;

  /**
   * @param layout The layout this Mixin belongs to.
   * @param attrs XML attributes given to the layout.
   * @param defStyleAttr The default style attribute as given to the constructor of the layout.
   */
  public HeaderMixin(
      @NonNull TemplateLayout layout, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
    templateLayout = layout;

    final TypedArray a =
        layout
            .getContext()
            .obtainStyledAttributes(attrs, R.styleable.SucHeaderMixin, defStyleAttr, 0);

    // Set the header text
    final CharSequence headerText = a.getText(R.styleable.SucHeaderMixin_sucHeaderText);
    if (headerText != null) {
      setText(headerText);
    }
    // Set the header text color
    final ColorStateList headerTextColor =
        a.getColorStateList(R.styleable.SucHeaderMixin_sucHeaderTextColor);
    if (headerTextColor != null) {
      setTextColor(headerTextColor);
    }

    a.recycle();
  }

  /** @return The TextView displaying the header. */
  public TextView getTextView() {
    return (TextView) templateLayout.findManagedViewById(R.id.suc_layout_title);
  }

  /**
   * Sets the header text. This can also be set via the XML attribute {@code app:sucHeaderText}.
   *
   * @param title The resource ID of the text to be set as header.
   */
  public void setText(int title) {
    final TextView titleView = getTextView();
    if (titleView != null) {
      titleView.setText(title);
    }
  }

  /**
   * Sets the header text. This can also be set via the XML attribute {@code app:sucHeaderText}.
   *
   * @param title The text to be set as header.
   */
  public void setText(CharSequence title) {
    final TextView titleView = getTextView();
    if (titleView != null) {
      titleView.setText(title);
    }
  }

  /** @return The current header text. */
  public CharSequence getText() {
    final TextView titleView = getTextView();
    return titleView != null ? titleView.getText() : null;
  }

  /**
   * Sets the color of the header text. This can also be set via XML using {@code
   * app:sucHeaderTextColor}.
   *
   * @param color The text color of the header.
   */
  public void setTextColor(ColorStateList color) {
    final TextView titleView = getTextView();
    if (titleView != null) {
      titleView.setTextColor(color);
    }
  }

  /** Returns the current text color of the header. */
  public ColorStateList getTextColor() {
    final TextView titleView = getTextView();
    return titleView != null ? titleView.getTextColors() : null;
  }
}