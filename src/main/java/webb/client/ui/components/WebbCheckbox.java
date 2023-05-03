package webb.client.ui.components;

import java.awt.image.BufferedImage;
import webb.client.ui.constants.WebbImages;

public class WebbCheckbox extends WebbButton {

    private boolean checked = false;

    public WebbCheckbox(CheckboxClickListener clickListener) {
        this(false, clickListener);
    }

    public WebbCheckbox(boolean initialChecked) {
        this(initialChecked, null);
    }

    public WebbCheckbox(boolean initialChecked, CheckboxClickListener clickListener) {
        super(getIcon(initialChecked), 64, 64, (self, rightClicked) -> {
            WebbCheckbox self2 = (WebbCheckbox) self;
            self2.setChecked(!self2.isChecked());

            if(clickListener != null) {
                clickListener.onClick(self2, self2.isChecked());
            }
        });
        this.checked = initialChecked;
        this.setDrawButtonOutline(true);
        this.setDrawBackground(true);
    }

    private static BufferedImage getIcon(boolean checked) {
        if(checked) {
            return WebbImages.CHECKBOX_CHECKED;
        } else {
            return WebbImages.CHECKBOX_EMPTY;
        }
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        setImage(getIcon(checked));
    }

    public boolean isChecked() {return checked;}

    public static interface CheckboxClickListener {
        /**
         * Called when the checkbox is clicked.
         * @param self The checkbox that was clicked.
         * @param isChecked Whether or not the checkbox is checked.
         */
        void onClick(WebbCheckbox self, boolean isChecked);
    }
}
