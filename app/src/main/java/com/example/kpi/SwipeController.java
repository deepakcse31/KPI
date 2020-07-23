package com.example.kpi;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.android.volley.VolleyLog;

import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_SWIPE;
import static com.android.volley.VolleyLog.TAG;

enum ButtonsState {
    GONE,
    LEFT_VISIBLE,
    RIGHT_VISIBLE,
    LEFT_VISIBLE1,
    RIGHT_VISIBLE1

}
public class SwipeController extends ItemTouchHelper.Callback {


    private boolean swipeBack = false;

    private ButtonsState buttonShowedState = ButtonsState.GONE;
    private ButtonsState buttonShowedState1 = ButtonsState.GONE;

    private RectF buttonInstance = null;

    private RecyclerView.ViewHolder currentItemViewHolder = null;

    private SwipeControllerAction buttonsActions = null;

    private static final float buttonWidth = 300;

    public SwipeController(SwipeControllerAction buttonsActions) {
        this.buttonsActions = buttonsActions;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        if (swipeBack) {
            swipeBack = buttonShowedState != ButtonsState.GONE;
            return 0;
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ACTION_STATE_SWIPE) {
            if (buttonShowedState != ButtonsState.GONE) {
                //distance kitna dikhaga
                   if (buttonShowedState == ButtonsState.LEFT_VISIBLE) dX = Math.max(dX, 550);
                   if (buttonShowedState == ButtonsState.LEFT_VISIBLE1) dX = Math.max(dX, 500);
                   if (buttonShowedState == ButtonsState.RIGHT_VISIBLE) dX = Math.min(dX, -550);//buttonwidth
                   if (buttonShowedState == ButtonsState.RIGHT_VISIBLE1) dX = Math.min(dX, -550);
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
            else {
                setTouchListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }

        if (buttonShowedState == ButtonsState.GONE) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
        currentItemViewHolder = viewHolder;
    }

    private void setTouchListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final float dX, final float dY, final int actionState, final boolean isCurrentlyActive) {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                swipeBack = event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP;
                if (swipeBack) {
                    if (dX < -buttonWidth){
                        buttonShowedState = ButtonsState.RIGHT_VISIBLE;
                        buttonShowedState1  = ButtonsState.RIGHT_VISIBLE1;
                      //  Log.e(TAG, "onTouch: "+buttonShowedState );
                      //  Log.e(TAG, "onTouch: "+buttonShowedState1 );

                    }
                     if (dX < -buttonWidth){
                        // buttonShowedState = ButtonsState.RIGHT_VISIBLE1;
                      //   buttonShowedState1  = ButtonsState.RIGHT_VISIBLE;
                      //  Log.e(TAG, "onTouch: "+buttonShowedState );
                    }
                     if (dX > buttonWidth){
                         buttonShowedState  = ButtonsState.LEFT_VISIBLE;
                         buttonShowedState1  = ButtonsState.LEFT_VISIBLE1;

                       // Log.e(TAG, "onTouch: "+buttonShowedState + "  dX "+ dX);
                        // Log.e(TAG, "onTouch: "+buttonShowedState1 + "  dX "+ dX);

                     }
                    if (dX > buttonWidth){
                      //  buttonShowedState1  = ButtonsState.LEFT_VISIBLE1;
                      //  buttonShowedState  = ButtonsState.LEFT_VISIBLE;

                      //  Log.e(TAG, "onTouch: "+buttonShowedState );
                    }



                    if (buttonShowedState != ButtonsState.GONE) {
                        setTouchDownListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                        setItemsClickable(recyclerView, false);
                    }
                }
                return false;
            }
        });
    }

    private void setTouchDownListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final float dX, final float dY, final int actionState, final boolean isCurrentlyActive) {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setTouchUpListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
                return false;
            }
        });
    }
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    private void setTouchUpListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final float dX, final float dY, final int actionState, final boolean isCurrentlyActive) {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    SwipeController.super.onChildDraw(c, recyclerView, viewHolder, 0F, dY, actionState, isCurrentlyActive);
                    recyclerView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {

                            return false;
                        }
                    });
                    setItemsClickable(recyclerView, true);
                    swipeBack = false;
                    Log.d("Error->","Error"+buttonsActions);
                    Log.d("Error1","Error1"+buttonInstance.contains(event.getX(), event.getY()));
                    Log.d("Error2","Error No->"+event.getX());
                    Log.d("Error3","Error No->"+event.getY());
               //   if (buttonsActions != null && buttonInstance != null && buttonInstance.contains(event.getX(), event.getY())) {

                        if (buttonShowedState == ButtonsState.LEFT_VISIBLE && event.getX()<300 ) {
                            Log.e(TAG, "onTouch: "+event.getX() );
                            buttonsActions.onLeftClicked(viewHolder.getAdapterPosition());
                            Log.e(TAG, "onTouch: LEFTVISIBLE "+ viewHolder.getAdapterPosition());
                            Log.e(TAG, "onTouch: BS"+buttonShowedState +" BS1 "+buttonShowedState1 );
                        //    buttonsActions.onLeftClicked1(viewHolder.getAdapterPosition());


                        }

                      else  if (buttonShowedState1==ButtonsState.LEFT_VISIBLE1 || (event.getX()>300 && event.getX()<600 ))
                        {
                            Log.e(TAG, "onTouch: "+event.getX() );
                            buttonsActions.onLeftClicked1(viewHolder.getAdapterPosition());
                            Log.e(TAG, "onTouch: LEFTVISIBLE 1 "+ viewHolder.getAdapterPosition());
                        }
                         if (buttonShowedState == ButtonsState.RIGHT_VISIBLE && event.getX()>getScreenWidth()-300 ) {
                             Log.e(TAG, "onTouch: "+getScreenWidth() );
                             Log.e(TAG, "onTouch: "+event.getX() );
                          buttonsActions.onRightClicked(viewHolder.getAdapterPosition());
                          Log.e(TAG, "onTouch: RIGHTVISIBLE "+ viewHolder.getAdapterPosition());
                         }
                        else if (buttonShowedState1==ButtonsState.RIGHT_VISIBLE1 && event.getX()<getScreenWidth()-300)
                        {
                            Log.e(TAG, "onTouch: "+event.getX() );
                            buttonsActions.onRightClicked1(viewHolder.getAdapterPosition());
                            Log.e(TAG, "onTouch: RIGHTVISIBLE 1 "+ viewHolder.getAdapterPosition());
                        }
                        //experiment

                    }
                     buttonShowedState = ButtonsState.GONE;
                  //   buttonShowedState1 = ButtonsState.GONE;
                    currentItemViewHolder = null;
               // }
                return false;
            }
        });
    }

    private void setItemsClickable(RecyclerView recyclerView, boolean isClickable) {
        for (int i = 0; i < recyclerView.getChildCount(); ++i) {
            recyclerView.getChildAt(i).setClickable(isClickable);
        }
    }

    public void drawButtons(Canvas c, RecyclerView.ViewHolder viewHolder) {
        float buttonWidthWithoutPadding = buttonWidth - 20;
        float buttonWidthWithoutPadding1 = buttonWidth + 50;
        float corners = 16;

        View itemView = viewHolder.itemView;
        Paint p = new Paint();
        int leftSpace= itemView.getLeft();
        RectF leftButton = new RectF(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + buttonWidthWithoutPadding, itemView.getBottom());
        p.setColor(Color.rgb(133, 206, 250));
       // p.setStyle(Style.);

        c.drawRoundRect(leftButton, corners, corners, p);
        drawText("Info", c, leftButton, p);

        //experiment
       RectF leftButton1 = new RectF(itemView.getLeft()+300, itemView.getTop(), itemView.getLeft() + buttonWidthWithoutPadding+260, itemView.getBottom());
        p.setColor(Color.RED);
        c.drawRoundRect(leftButton1, corners, corners, p);
        drawText("Trend", c, leftButton1, p);




        RectF rightButton = new RectF(itemView.getRight() - buttonWidthWithoutPadding, itemView.getTop(), itemView.getRight(), itemView.getBottom());
        p.setColor(Color.RED);
        c.drawRoundRect(rightButton, corners, corners, p);
        drawText("DELETE", c, rightButton, p);


        RectF rightButton1 = new RectF(itemView.getRight() - buttonWidthWithoutPadding-250, itemView.getTop(), itemView.getRight()-303, itemView.getBottom());
        p.setColor(Color.BLUE);
        c.drawRoundRect(rightButton1, corners, corners, p);

        drawText("DELETE", c, rightButton1, p);
        buttonInstance = null;

        if (buttonShowedState == ButtonsState.LEFT_VISIBLE && leftSpace == itemView.getLeft() ) {
            buttonInstance = leftButton;
            Log.e(TAG, "drawButtons: lb1 "+buttonInstance );
        }
        else if (buttonShowedState == ButtonsState.LEFT_VISIBLE1 && leftSpace == itemView.getLeft())
        {
            buttonInstance=leftButton1;
            Log.e(TAG, "drawButtons: lb2"+buttonInstance );
        }
        else if (buttonShowedState == ButtonsState.RIGHT_VISIBLE) {
            buttonInstance = rightButton;
            Log.e(TAG, "drawButtons: rb1 "+buttonInstance );
        }
        else if (buttonShowedState==ButtonsState.RIGHT_VISIBLE1)
        {
                buttonInstance=rightButton1;
            Log.e(TAG, "drawButtons: rb2 "+buttonInstance );

        }
        //experiment

    }

    private void drawText(String text, Canvas c, RectF button, Paint p) {
        float textSize = 60;
        p.setColor(Color.WHITE);
        p.setAntiAlias(true);
        p.setTextSize(textSize);

        float textWidth = p.measureText(text);
        c.drawText(text, button.centerX()-(textWidth/2), button.centerY()+(textSize/2), p);

    }

    public void onDraw(Canvas c) {
        if (currentItemViewHolder != null) {
            drawButtons(c, currentItemViewHolder);
        }
    }
}
