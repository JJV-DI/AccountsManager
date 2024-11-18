package model;

public class ViewManager {
    private ViewStatus viewStatus;

    public ViewStatus getViewStatus() {
        return viewStatus;
    }

    public void setStatus(ViewStatus newStatus) {
        this.viewStatus = newStatus;
    }
}
