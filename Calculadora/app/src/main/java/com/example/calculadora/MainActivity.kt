import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora.R

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private var firstNumber: Double = 0.0
    private var operator: String = ""
    private var isOperatorClicked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
    }

    fun onNumberClick(view: View) {
        if (view is Button) {
            val number: String = view.text.toString()
            val currentText: String = editText.text.toString()

            if (currentText == "0" || isOperatorClicked) {
                editText.setText(number)
                isOperatorClicked = false
            } else {
                editText.setText(currentText + number)
            }
        }
    }

    fun onOperatorClick(view: View) {
        if (view is Button) {
            val operatorButton: String = view.text.toString()
            val currentText: String = editText.text.toString()

            if (currentText.isNotEmpty() && !isOperatorClicked) {
                firstNumber = currentText.toDouble()
                operator = operatorButton
                editText.setText(currentText + operatorButton)
                isOperatorClicked = true
            }
        }
    }

    fun onEqualsClick(view: View) {
        val currentText: String = editText.text.toString()

        if (currentText.isNotEmpty() && isOperatorClicked) {
            val secondNumber: Double = currentText.substringAfterLast(operator).toDouble()
            val result: Double = when (operator) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "*" -> firstNumber * secondNumber
                "/" -> firstNumber / secondNumber
                else -> 0.0
            }
            editText.setText(result.toString())
            isOperatorClicked = false
        }
    }

    fun onClearClick(view: View) {
        editText.setText("0")
        firstNumber = 0.0
        operator = ""
        isOperatorClicked = false
    }
}
