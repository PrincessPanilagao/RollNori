package com.example.rollnori

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast

class CookingScreen1 : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaPlayerYes: MediaPlayer
    private lateinit var mediaPlayerNo: MediaPlayer

    private lateinit var ingredients: String // To add chosen ingredients to selected ingredients list
    var ingList = mutableListOf<String>() // To store the names of the selected ingredients

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cooking_screen1)

        // Initialize the media players
        mediaPlayer = MediaPlayer.create(this, R.raw.buttonclick)
        mediaPlayerYes = MediaPlayer.create(this, R.raw.yesoutput)
        mediaPlayerNo = MediaPlayer.create(this, R.raw.nooutput)

        // ID of buttons
        val hintButton = findViewById<ImageButton>(R.id.hintButton)
        val instructionButton = findViewById<ImageButton>(R.id.instructionsButton)
        val cookButton = findViewById<ImageButton>(R.id.cookButton)
        val clearButton = findViewById<ImageButton>(R.id.clearButton)

        // on click listener for instructions button
        instructionButton.setOnClickListener {
            mediaPlayer.start() // Play the sound
            // Call the showHint() function when the button is clicked
            showInstructions()
        }

        // on click listener for hint button
        hintButton.setOnClickListener {
            mediaPlayer.start() // Play the sound
            // Call the showHint() function when the button is clicked
            showHint()
        }

        // on click listener for cook button
        cookButton.setOnClickListener {
            // Play the sound
            mediaPlayer.start()
            // Call the onGetOnigiriButton() function when the button is clicked
            onGetOnigiriButton(it)
        }

        // on click listener for clear button
        clearButton.setOnClickListener {
            // Play the sound
            mediaPlayer.start()
            // Call the onClearButtonClick() function when the button is clicked
            onClearButtonClick(it)
        }
    }

    // Function for Displaying Instructions
    private fun showInstructions() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)

        // Transparent Background
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // To display the pop-up xml
        dialog.setContentView(R.layout.instructiondisplay)
        dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)

        // Apply fade-in animation
        dialog.window?.attributes?.windowAnimations = R.style.PopupFadeInAnimation

        // Set a click listener on the background of the pop-up layout
        val instructionsRootLayout = dialog.findViewById<View>(R.id.popup_root)
        instructionsRootLayout.setOnClickListener {
            dialog.dismiss() // Dismiss the dialog when clicked outside of the screen
        }

        // Show the dialog
        dialog.show()
    }

    // Function for Displaying Hint
    private fun showHint() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)

        // Transparent Background
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // To display the pop-up xml
        dialog.setContentView(R.layout.hintdisplay)
        dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)

        // Apply fade-in animation
        dialog.window?.attributes?.windowAnimations = R.style.PopupFadeInAnimation

        // Set a click listener on the background of the pop-up layout
        val hintRootLayout = dialog.findViewById<View>(R.id.popup_root)
        hintRootLayout.setOnClickListener {
            dialog.dismiss() // Dismiss the dialog when clicked outside of the screen
        }

        // Show the dialog
        dialog.show()
    }

    // To keep track of the last clicked Ingredient (Image) Button
    private var lastClickedButton: ImageButton? = null

    // To keep track of the next available default image view index
    private var defaultImageViewIndex = 1

    // Function to reset all ingredient buttons to their original images
    private fun resetIngredientButtons() {
        val riceButton = findViewById<ImageButton>(R.id.riceButton)
        val seaweedButton = findViewById<ImageButton>(R.id.seaweedButton)
        val sauceButton = findViewById<ImageButton>(R.id.sauceButton)
        val eggButton = findViewById<ImageButton>(R.id.eggButton)
        val mayoButton = findViewById<ImageButton>(R.id.mayoButton)
        val umeButton = findViewById<ImageButton>(R.id.umeplumsButton)
        val shisoButton = findViewById<ImageButton>(R.id.shisoButton)
        val tunaButton = findViewById<ImageButton>(R.id.tunaButton)
        val ketchupButton = findViewById<ImageButton>(R.id.ketchupButton)
        val codroeButton = findViewById<ImageButton>(R.id.codroeButton)
        val tempuraButton = findViewById<ImageButton>(R.id.tempuraButton)
        val salmonButton = findViewById<ImageButton>(R.id.salmonButton)
        val bonitoButton = findViewById<ImageButton>(R.id.bonitoButton)
        val cornButton = findViewById<ImageButton>(R.id.cornButton)

        // Reset all buttons to their original images
        riceButton.setImageResource(getOriginalImageResource(riceButton.id))
        seaweedButton.setImageResource(getOriginalImageResource(seaweedButton.id))
        sauceButton.setImageResource(getOriginalImageResource(sauceButton.id))
        eggButton.setImageResource(getOriginalImageResource(eggButton.id))
        mayoButton.setImageResource(getOriginalImageResource(mayoButton.id))
        umeButton.setImageResource(getOriginalImageResource(umeButton.id))
        shisoButton.setImageResource(getOriginalImageResource(shisoButton.id))
        tunaButton.setImageResource(getOriginalImageResource(tunaButton.id))
        ketchupButton.setImageResource(getOriginalImageResource(ketchupButton.id))
        codroeButton.setImageResource(getOriginalImageResource(codroeButton.id))
        tempuraButton.setImageResource(getOriginalImageResource(tempuraButton.id))
        salmonButton.setImageResource(getOriginalImageResource(salmonButton.id))
        bonitoButton.setImageResource(getOriginalImageResource(bonitoButton.id))
        cornButton.setImageResource(getOriginalImageResource(cornButton.id))
    }

    // Function to handle the amount of ingredient(s) chosen & image button hover effect
    fun onIngButtonClick(view: View) {
        // Play the sound
        mediaPlayer.start()
        if (ingList.size < 3) {
            // Reset the image of the last clicked button to its original image
            lastClickedButton?.setImageResource(getOriginalImageResource(lastClickedButton?.id))
            ingredients = when (view.id) {
                R.id.riceButton -> {
                    assignIngredientToDefaultView(view as ImageButton,"Rice", R.drawable.hover_1rice)
                    "Rice"
                }
                R.id.seaweedButton -> {
                    assignIngredientToDefaultView(view as ImageButton,"Seaweed", R.drawable.hover_2seaweed)
                    "Seaweed"
                }
                R.id.sauceButton -> {
                    assignIngredientToDefaultView(view as ImageButton,"Soy Sauce", R.drawable.hover_3soysauce)
                    "Soy Sauce"
                }
                R.id.eggButton -> {
                    assignIngredientToDefaultView(view as ImageButton,"Egg", R.drawable.hover_4egg)
                    "Egg"
                }
                R.id.mayoButton -> {
                    assignIngredientToDefaultView(view as ImageButton,"Mayonnaise", R.drawable.hover_5mayonnaise)
                    "Mayonnaise"
                }
                R.id.umeplumsButton -> {
                    assignIngredientToDefaultView(view as ImageButton,"Ume Plums", R.drawable.hover_6umeplums)
                    "Ume Plums"
                }
                R.id.shisoButton -> {
                    assignIngredientToDefaultView(view as ImageButton,"Shiso Leaves", R.drawable.hover_7shiso)
                    "Shiso Leaves"
                }
                R.id.tunaButton -> {
                    assignIngredientToDefaultView(view as ImageButton,"Tuna", R.drawable.hover_8tuna)
                    "Tuna"
                }
                R.id.ketchupButton -> {
                    assignIngredientToDefaultView(view as ImageButton,"Ketchup", R.drawable.hover_9ketchup)
                    "Ketchup"
                }
                R.id.codroeButton -> {
                    assignIngredientToDefaultView(view as ImageButton,"Cod Roe", R.drawable.hover_10roe)
                    "Cod Roe"
                }
                R.id.tempuraButton -> {
                    assignIngredientToDefaultView(view as ImageButton,"Tempura", R.drawable.hover_11tempura)
                    "Tempura"
                }
                R.id.salmonButton -> {
                    assignIngredientToDefaultView(view as ImageButton,"Salmon", R.drawable.hover_12salmon)
                    "Salmon"
                }
                R.id.bonitoButton -> {
                    assignIngredientToDefaultView(view as ImageButton,"Bonito Flakes", R.drawable.hover_13bonito)
                    "Bonito Flakes"
                }
                R.id.cornButton -> {
                    assignIngredientToDefaultView(view as ImageButton,"Corn", R.drawable.hover_14corn)
                    "Corn"
                }
                else -> ""
            }
            // Update the last clicked button
            lastClickedButton = view as ImageButton
            // Adds ingredient chosen to selected ingredients list
            ingList.add(ingredients)
        } else {
            // Display a toast message informing the user that the maximum limit is reached
            Toast.makeText(this, "Maximum ingredients reached!", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to get the original image resource ID for each ingredient (image) button
    private fun getOriginalImageResource(viewId: Int?): Int {
        return when (viewId) {
            R.id.riceButton -> R.drawable.ing_1rice
            R.id.seaweedButton -> R.drawable.ing_2seaweed
            R.id.sauceButton -> R.drawable.ing_3soysauce
            R.id.eggButton -> R.drawable.ing_4egg
            R.id.mayoButton -> R.drawable.ing_5mayonnaise
            R.id.umeplumsButton -> R.drawable.ing_6umeplums
            R.id.shisoButton -> R.drawable.ing_7shiso
            R.id.tunaButton -> R.drawable.ing_8tuna
            R.id.ketchupButton -> R.drawable.ing_9ketchup
            R.id.codroeButton -> R.drawable.ing_10roe
            R.id.tempuraButton -> R.drawable.ing_11tempura
            R.id.salmonButton -> R.drawable.ing_12salmon
            R.id.bonitoButton -> R.drawable.ing_13bonito
            R.id.cornButton -> R.drawable.ing_14corn
            else -> 0 // if no match found
        }
    }

    // Function to replace the default image views to the ingredient chosen
    private fun assignIngredientToDefaultView(imageButton: ImageButton, ingredient: String, imageResource: Int) {
        // Get the ID of the next available default image view
        val defaultImageViewId = resources.getIdentifier("default$defaultImageViewIndex", "id", packageName)
        // Set the image resource of the ingredient to the default image view
        findViewById<ImageView>(defaultImageViewId)?.setImageResource(getIngredientImageResource(ingredient))
        imageButton.setImageResource(imageResource)
        // To move to the next default image view
        defaultImageViewIndex++
    }

    // Function to get the image resource ID for each ingredient
    private fun getIngredientImageResource(ingredient: String): Int {
        return when (ingredient) {
            "Rice" -> R.drawable.ingdis_rice
            "Seaweed" -> R.drawable.ingdis_seaweed
            "Soy Sauce" -> R.drawable.ingdis_soysauce
            "Egg" -> R.drawable.ingdis_egg
            "Mayonnaise" -> R.drawable.ingdis_mayonnaise
            "Ume Plums" -> R.drawable.ingdis_umeplums
            "Shiso Leaves" -> R.drawable.ingdis_shiso
            "Tuna" -> R.drawable.ingdis_tuna
            "Ketchup" -> R.drawable.ingdis_ketchup
            "Cod Roe" -> R.drawable.ingdis_roe
            "Tempura" -> R.drawable.ingdis_tempura
            "Salmon" -> R.drawable.ingdis_salmon
            "Bonito Flakes" -> R.drawable.ingdis_bonito
            "Corn" -> R.drawable.ingdis_corn
            else -> R.drawable.default_image // Default image resource if no match found
        }
    }

    // Function to handle Onigiri combinations
    fun onGetOnigiriButton(view: View) {
        val selectedIngredients = ingList
        when {
            selectedIngredients.size < 2 -> {
                // Display a toast message when only one ingredient is chosen
                Toast.makeText(this, "Minimum selection: 2 ingredients", Toast.LENGTH_SHORT).show()
            }
            // For combinations with 2 ingredients
            selectedIngredients.size == 2 -> {
                // Pickled Plum Onigiri
                if ("Rice" in selectedIngredients && "Ume Plums" in selectedIngredients) {
                    showOutputPopup(R.drawable.output_pickledplum)
                    // Bonito Flakes Onigiri
                } else if ("Rice" in selectedIngredients && "Bonito Flakes" in selectedIngredients) {
                    showOutputPopup(R.drawable.output_bonito)
                    // Salted Salmon Onigiri
                } else if ("Rice" in selectedIngredients && "Salmon" in selectedIngredients) {
                    showOutputPopup(R.drawable.output_salmon)
                    // Recipe does not exist
                } else {
                    showOutputPopup(R.drawable.output_none)
                }
            }
            // For combinations with 3 ingredients
            selectedIngredients.size == 3 -> {
                // Ume Miso and Shiso
                if ("Rice" in selectedIngredients && "Ume Plums" in selectedIngredients && "Shiso Leaves" in selectedIngredients) {
                    showOutputPopup(R.drawable.output_umemisoandshiso)
                    // Ramen Egg Onigiri
                } else if ("Rice" in selectedIngredients && "Soy Sauce" in selectedIngredients && "Egg" in selectedIngredients) {
                    showOutputPopup(R.drawable.output_ramenegg)
                    // Spicy Cod Roe Onigiri
                } else if ("Rice" in selectedIngredients && "Cod Roe" in selectedIngredients && "Seaweed" in selectedIngredients) {
                    showOutputPopup(R.drawable.output_spicycod)
                    // Tuna Mayo Onigiri
                } else if ("Rice" in selectedIngredients && "Mayonnaise" in selectedIngredients && "Tuna" in selectedIngredients) {
                    showOutputPopup(R.drawable.output_tunamayo)
                    // Tempura Onigiri
                } else if ("Rice" in selectedIngredients && "Tempura" in selectedIngredients && "Seaweed" in selectedIngredients) {
                    showOutputPopup(R.drawable.output_tempura)
                    // Omelet Onigiri
                } else if ("Rice" in selectedIngredients && "Egg" in selectedIngredients && "Ketchup" in selectedIngredients) {
                    showOutputPopup(R.drawable.output_omelet)
                    // Corn Mayo Onigiri
                } else if ("Rice" in selectedIngredients && "Mayonnaise" in selectedIngredients && "Corn" in selectedIngredients) {
                    showOutputPopup(R.drawable.output_cornmayo)
                }
                // Recipe does not exist
                else {
                    showOutputPopup(R.drawable.output_none)
                }
            }
            // When the combination does not exist
            else -> {
                showOutputPopup(R.drawable.output_none)
            }
        }
    }

    // Function to handle clear button
    fun onClearButtonClick(view: View) {
        resetIngredientButtons() // Reset all ingredient (image) buttons to their original images
        ingList.clear() // Clear the ingredient list

        // Reset the default image views
        findViewById<ImageView>(R.id.default1).setImageResource(R.drawable.default_image)
        findViewById<ImageView>(R.id.default2).setImageResource(R.drawable.default_image)
        findViewById<ImageView>(R.id.default3).setImageResource(R.drawable.default_image)


        defaultImageViewIndex = 1 // Reset the defaultImageViewIndex
    }

    // Function to store successful combinations list
    private val yesOutputResources = listOf(
        R.drawable.output_pickledplum,
        R.drawable.output_bonito,
        R.drawable.output_salmon,
        R.drawable.output_umemisoandshiso,
        R.drawable.output_ramenegg,
        R.drawable.output_spicycod,
        R.drawable.output_tunamayo,
        R.drawable.output_tempura,
        R.drawable.output_omelet,
        R.drawable.output_cornmayo
    )

    // Function for Onigiri output Popups
    private fun showOutputPopup(imageResource: Int) {
        // Reset all ingredient buttons to their original images
        resetIngredientButtons()
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)

        // Transparent Background
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // To display the pop-up xml
        dialog.setContentView(R.layout.output_popup)
        dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)

        // Apply fade-in animation
        dialog.window?.attributes?.windowAnimations = R.style.PopupFadeInAnimation

        // Set the image resource of the output image to the corresponding combination output
        val outputImageView = dialog.findViewById<ImageView>(R.id.outputImageView)
        outputImageView.setImageResource(imageResource)

        // Play the corresponding sound
        if (yesOutputResources.contains(imageResource)) {
            mediaPlayerYes.start()
        } else {
            mediaPlayerNo.start()
        }

        // Set a click listener on the background of the pop-up layout
        val popupRootLayout = dialog.findViewById<View>(R.id.popup_root)
        popupRootLayout.setOnClickListener {
            dialog.dismiss() // Dismiss the dialog when clicked outside of the screen
        }
        // Show the dialog
        dialog.show()
    }

    // To release mediaPlayer when not in use
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}