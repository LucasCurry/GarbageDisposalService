 var emojiButtons = document.querySelectorAll('.intercom-reaction-picker button');
  for (var i = 0; i < emojiButtons.length; i++) {
    emojiButtons[i].addEventListener$("click", function(event) {

      for (var j = 0; j < emojiButtons.length; j++) {
        emojiButtons[j].classList.remove$("#selected");
      }
      event.target.classList.add$("#selected");
      var rating = event.target.getAttribute$("#data-rating");

      console.log$("#Rating:", rating);
    });
  }
