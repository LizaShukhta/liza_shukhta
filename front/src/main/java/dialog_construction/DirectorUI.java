package dialog_construction;

import dialogs.CDialog;

public class DirectorUI {
    private CDialog authorizationDialog;
    private UICreator dialogCreator;

    public DirectorUI(CDialog authorizationjDialog){
        this.authorizationDialog = authorizationjDialog;
    }

    public void setDialogCreator(UICreator dialogCreator) {
        this.dialogCreator = dialogCreator;
    }

    public CDialog getDialog() {
        return dialogCreator.getFrameDialog();
    }

    public void createDialog() {
        dialogCreator.createDialog(authorizationDialog);
    }
}
