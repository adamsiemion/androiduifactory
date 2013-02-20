package com.adko.androiduifactory;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

class ViewFactory <V extends View> {
	private V view;
	private RelativeLayout.LayoutParams params;
	private RelativeLayout layout;

	public ViewFactory(V view, RelativeLayout.LayoutParams params, RelativeLayout layout) {
		this.view = view;
		this.params = params != null ? new RelativeLayout.LayoutParams(params) : null;
		this.view.setLayoutParams(this.params);
		this.layout = layout;
	}		
	
	public ViewFactory<V> leftMargin(int margin) {
		this.params.leftMargin = margin;
		return this;
	}
	
	public ViewFactory<V> rightMargin(int margin) {
		this.params.rightMargin = margin;
		return this;
	}
	
	public ViewFactory<V> bottomMargin(int margin) {
		this.params.bottomMargin = margin;
		return this;
	}
	
	public ViewFactory<V> topMargin(int margin) {
		this.params.topMargin = margin;
		return this;
	}
	
	public ViewFactory<V> addRule(int verb, int anchor) {
		this.params.addRule(verb, anchor);
		return this;
	}
	
	public ViewFactory<V> below(View view) {
		return addRule(RelativeLayout.BELOW, view.getId());
	}
	
	public ViewFactory<V> above(View view) {
		return addRule(RelativeLayout.ABOVE, view.getId());
	}
	
	public ViewFactory<V> alignLeft(View view) {
		return addRule(RelativeLayout.ALIGN_LEFT, view.getId());
	}
	
	public ViewFactory<V> alignRight(View view) {
		return addRule(RelativeLayout.ALIGN_RIGHT, view.getId());
	}
	
	public V view() {
		return view;
	}

	public ViewFactory<V> addViewToLayout() {
		layout.addView(view);
		return this;
	}

}

class TextViewFactory <V extends TextView> extends ViewFactory<V> {
	private V view;

	public TextViewFactory(V view, RelativeLayout.LayoutParams params, RelativeLayout layout) {
		super(view, params, layout);
		this.view = view;
	}		
	
	public TextViewFactory<V> setText(CharSequence text) {
		view.setText(text);
		return this;
	}	
	
	public V view() {
		return view;
	}
}

class RelativeLayoutFactory {
	private RelativeLayout layout;
	private RelativeLayout.LayoutParams params, viewParams;
	private AndroidUIFactory factory;
	
	public static enum Size { 
		MATCH_PARENT (android.view.ViewGroup.LayoutParams.MATCH_PARENT),
		WRAP_CONTENT (android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		
		int size;
		private Size(int size) {
			this.size = size;
		}
	}
	
	public static enum Align {		
		ALIGN_BASELINE (RelativeLayout.ALIGN_BASELINE),
		ALIGN_BOTTOM (RelativeLayout.ALIGN_BOTTOM),
		ALIGN_END (RelativeLayout.ALIGN_END),
		ALIGN_LEFT (RelativeLayout.ALIGN_LEFT),
		ALIGN_PARENT_BOTTOM (RelativeLayout.ALIGN_PARENT_BOTTOM),
		ALIGN_PARENT_END (RelativeLayout.ALIGN_PARENT_END),
		ALIGN_PARENT_LEFT (RelativeLayout.ALIGN_PARENT_LEFT),
		ALIGN_PARENT_RIGHT (RelativeLayout.ALIGN_PARENT_RIGHT),
		ALIGN_PARENT_START (RelativeLayout.ALIGN_PARENT_START),
		ALIGN_PARENT_TOP (RelativeLayout.ALIGN_PARENT_TOP);
				
		private int verb;
		private Align(int verb) {
			this.verb = verb;
		}
	}
	
	public RelativeLayoutFactory(AndroidUIFactory factory, RelativeLayout layout) {
		this.factory = factory;
		this.layout = layout;
	}
	
	public RelativeLayout layout() {
		return layout;
	}

	public RelativeLayoutFactory viewSize(Size width, Size height) {
		viewParams = new RelativeLayout.LayoutParams(width.size, height.size);
		return this;
	}
	
	public RelativeLayoutFactory layoutSize(Size width, Size height) {
		params = new RelativeLayout.LayoutParams(width.size, height.size);
		layout.setLayoutParams(params);
		return this;
	}	
	
	public RelativeLayoutFactory align(Align... alignOptions) {
		for(Align align : alignOptions) {
			params.addRule(align.verb);
		}
		return this;
	}

	public <V extends View> ViewFactory<V> newView(V view) {
		ViewFactory<V> viewFactory = new ViewFactory<V>(view, viewParams, layout);
		view.setId(factory.nextId());
		return viewFactory;
	}
	
	public <V extends TextView> TextViewFactory<V> newTextView(V view) {		
		TextViewFactory<V> viewFactory = new TextViewFactory<V>(view, viewParams, layout);	
		view.setId(factory.nextId());
		return viewFactory;
	}
	
}

/**
 * <p>Simplifies building of Android UI by reliving you from having to write boilerplate Android UI code. </p> 
 * 
 * <p>Instead of writing code like: </p>
 * <pre>
 *	final Button buttonCancel = new Button(this);
 *	buttonCancel.setText("Cancel");
 *	buttonCancel.setId(300);
 *	final RelativeLayout.LayoutParams buttonCancelParams = new RelativeLayout.LayoutParams(
 *		RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
 *	buttonCancelParams.topMargin = 20;
 *	buttonCancelParams.addRule(RelativeLayout.BELOW, buttonOk.getId());
 *	buttonCancelParams.addRule(RelativeLayout.ALIGN_RIGHT, checkbox2.getId());
 *	buttonCancelParams.addRule(RelativeLayout.ALIGN_LEFT, checkbox2.getId());
 *	buttonCancel.setLayoutParams(buttonCancelParams);
 *	layout.addView(buttonCancel);
 * </pre>			
 * 
 * <p>With Android UI Factory the above can be shorten to:</p>
 * <pre>
 *	final Button buttonCancel = layoutFactory.newTextView(new Button(this))
 *		.setText("Cancel")
 * 		.topMargin(20)
 * 		.below(buttonOk)
 * 		.alignLeft(checkbox2)
 *		.alignRight(checkbox2)
 * 		.addViewToLayout()
 * 		.view();	
 * </pre>
 * 
 * @author Adam Siemion
 *
 */
public class AndroidUIFactory {
	private Context context;
	private int id = 100;

	public AndroidUIFactory(Context context) {
		this.context = context;
	}
	
	public int nextId() {
		return id++;

	}
	public RelativeLayoutFactory newRelativeLayout() {
		return new RelativeLayoutFactory(this, new RelativeLayout(context));
	}
	
	
}
