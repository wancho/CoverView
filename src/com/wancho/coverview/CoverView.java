package com.wancho.coverview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class CoverView extends RelativeLayout {

	private ViewGroup vpContainerParent;

	private View viewTarget;

	private int index;

	public CoverView(Context context) {
		super(context);
	}

	public CoverView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void showTargetView(View viewTarget) {
		ViewGroup vpParent = (ViewGroup) getParent();
		if (vpParent != null) {
			return;
		}

		if (viewTarget == null) {
			return;
		}
		this.viewTarget = viewTarget;
		
		ViewGroup vpTargetViewParent = (ViewGroup) viewTarget.getParent();
		if(vpTargetViewParent == null) {
			return;
		}
		this.vpContainerParent = vpTargetViewParent;
		
		int viewTargetLeft = viewTarget.getLeft();
		int viewTargetTop = viewTarget.getTop();
		int viewTargetWidth = viewTarget.getWidth();
		int viewTargetHeight = viewTarget.getHeight();
		
		MarginLayoutParams lpMargin = null;
		if (vpContainerParent instanceof RelativeLayout) {
			lpMargin = new RelativeLayout.LayoutParams(viewTargetWidth, viewTargetHeight);
			lpMargin.topMargin = viewTargetTop;
			lpMargin.leftMargin = viewTargetLeft;
			vpContainerParent.addView(this, lpMargin);
		} else if (vpContainerParent instanceof FrameLayout) {
			lpMargin = new FrameLayout.LayoutParams(viewTargetWidth, viewTargetHeight);
			lpMargin.topMargin = viewTargetTop;
			lpMargin.leftMargin = viewTargetLeft;
			vpContainerParent.addView(this, lpMargin);
		} else if (vpContainerParent instanceof LinearLayout) {
			index = vpContainerParent.indexOfChild(viewTarget);
			android.view.ViewGroup.LayoutParams lpViewTarget = viewTarget.getLayoutParams();
			vpContainerParent.removeView(viewTarget);
			
			FrameLayout flContainer = new FrameLayout(getContext());
			flContainer.setLayoutParams(lpViewTarget);
			vpContainerParent.addView(flContainer, index);
			flContainer.addView(viewTarget, new FrameLayout.LayoutParams(viewTargetWidth, viewTargetHeight));
			flContainer.addView(this, new FrameLayout.LayoutParams(viewTargetWidth, viewTargetHeight));
		}
	}
	
	public void hide() {
		if (vpContainerParent == null) {
			return;
		}
		if (vpContainerParent instanceof RelativeLayout || vpContainerParent instanceof FrameLayout) {
			vpContainerParent.removeView(this);
		} else if (vpContainerParent instanceof LinearLayout) {
			ViewGroup viewContainer = (ViewGroup) vpContainerParent.getChildAt(index);
			if (viewContainer.indexOfChild(viewTarget) != -1) {
				viewContainer.removeView(viewTarget);
			}
			if (viewContainer.indexOfChild(this) != -1) {
				viewContainer.removeView(this);
			}
			if (viewContainer != null) {
				android.view.ViewGroup.LayoutParams lpViewContainer = viewContainer.getLayoutParams();
				vpContainerParent.removeView(viewContainer);
				vpContainerParent.addView(viewTarget, index, new LinearLayout.LayoutParams(lpViewContainer));
			}
		}
	}

}
