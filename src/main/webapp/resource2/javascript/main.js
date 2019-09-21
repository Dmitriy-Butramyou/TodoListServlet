'use strict';
function isSure() {
    var sure = confirm("Are you sure you want to delete the user? " +
        "Cancel the action will not be possible.");
    console.log(sure);
    if(sure) {
        location.href = '/button?command=USER_DELETE';
    }
}