var n = 0;
function addNewInputLine(){
        $(document).ready(function(){
       $("#answerParentElement").append(
       "<div class='row mb-1' id=ans"+n+" >"+
            "<div class=col-6><input type=text class=form-control name=answers required minlength=2></input></div>"+
            "<div class=col-2><input type=checkbox value=none name=correct_answers></div>"+
            "<div class=col-4><button onclick=deleteAnswer("+n+") name=deleteInputLine class='btn btn-danger' type=button >Remove answer</button></div>"+
       "</div>");
    });n++;
}
function deleteAnswer(fieldNumber){
    let confirmValue = confirm("Are you sure you want to delete?");
    if(confirmValue == true){
    }
    const element = document.getElementById("ans"+fieldNumber);
    element.remove();
}
function fillCheckboxValue() {

  if($('div.checkbox-group.required :checkbox:checked').length > 0){
    const answers = document.getElementsByName("answers");
    const correctAnswers = document.getElementsByName("correct_answers");
    for (let i = 0; i < answers.length; i++) {
      correctAnswers[i].value = answers[i].value;
    }
    return true;
  }
  else{
      alert("You have not chosen any correct answers. Please be sure to checkmark the correct answer near the answer field");
      return false;
  }
}
function upload_check()
{
  var uplImage = document.getElementById("image");
  var max = 1048576; //default 1mb 1024x1024
  if(uplImage.files[0].size > max)
  {
     alert("File too big! Select a file up to 1 Mb");
     uplImage.value = "";
  }
}