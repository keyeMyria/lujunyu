package com.bm.container.databinding;
import com.bm.container.R;
import com.bm.container.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class ItemDiscussBindingImpl extends ItemDiscussBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.face, 1);
        sViewsWithIds.put(R.id.name, 2);
        sViewsWithIds.put(R.id.content, 3);
        sViewsWithIds.put(R.id.all, 4);
        sViewsWithIds.put(R.id.image, 5);
        sViewsWithIds.put(R.id.model_zan, 6);
        sViewsWithIds.put(R.id.zan_pic, 7);
        sViewsWithIds.put(R.id.zan_num, 8);
        sViewsWithIds.put(R.id.model_reply, 9);
        sViewsWithIds.put(R.id.reply_img, 10);
        sViewsWithIds.put(R.id.reply_num, 11);
        sViewsWithIds.put(R.id.model_top, 12);
        sViewsWithIds.put(R.id.list, 13);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemDiscussBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private ItemDiscussBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[3]
            , (com.bm.container.view.CircleImageView) bindings[1]
            , (com.bm.container.view.ExtraGridView) bindings[5]
            , (com.bm.container.view.ExtraListView) bindings[13]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.RelativeLayout) bindings[12]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.TextView) bindings[2]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[8]
            , (android.widget.ImageView) bindings[7]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}