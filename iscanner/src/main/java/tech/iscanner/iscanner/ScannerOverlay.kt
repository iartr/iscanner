package tech.iscanner.iscanner

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.WindowManager

class ScannerOverlay @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleArr: Int = 0
) : View(context, attrs, defStyleArr) {

    private var leftCoordinate: Float = 0f
    private var topCoordinate: Float = 0f
    private var rightCoordinate: Float = 0f
    private var bottomCoordinate: Float = 0f

    private val cornerPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val eraser = Paint(Paint.ANTI_ALIAS_FLAG).apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }
    private val transparentRect = RectF()

    /**
     * Changeable view fields
     * Measure unit: dp
     */
    var topMargin: Int = 0
        set(value) {
            field = value
            invalidate()
        }
    var cornerSize: Int = 0
        set(value) {
            field = value
            invalidate()
        }
    var cornerWidth: Int = 2
        set(value) {
            field = value
            cornerPaint.strokeWidth = cornerWidth.dpToPx().toFloat()
            invalidate()
        }
    var cornerColor: Int = Color.WHITE
        set(value) {
            field = value
            cornerPaint.color = cornerColor
            invalidate()
        }
    var rectWidth: Int = 230
        set(value) {
            field = value
            invalidate()
        }
    var rectHeight: Int = 230
        set(value) {
            field = value
            invalidate()
        }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScannerOverlay)

        topMargin = typedArray.getInteger(
            R.styleable.ScannerOverlay_squareTopMargin,
            resources.getInteger(R.integer.scanner_rect_top_margin)
        )

        cornerSize = typedArray.getInteger(
            R.styleable.ScannerOverlay_cornersSize,
            resources.getInteger(R.integer.scanner_corner_size)
        ).dpToPx()

        cornerWidth = typedArray.getInteger(
            R.styleable.ScannerOverlay_cornersWidth,
            resources.getInteger(R.integer.scanner_corner_width)
        )

        cornerColor = typedArray.getColor(R.styleable.ScannerOverlay_cornersColor, Color.WHITE)

        rectWidth = typedArray.getInteger(
            R.styleable.ScannerOverlay_squareWidth,
            resources.getInteger(R.integer.scanner_rect_width)
        )

        rectHeight = typedArray.getInteger(
            R.styleable.ScannerOverlay_squareHeight,
            resources.getInteger(R.integer.scanner_rect_height)
        )

        cornerPaint.style = Paint.Style.STROKE
        cornerPaint.strokeWidth = cornerWidth.dpToPx().toFloat()
        cornerPaint.color = cornerColor

        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        calculateRectCoordinates()

        transparentRect.set(
            leftCoordinate,
            topCoordinate,
            rectWidth.dpToPx() + leftCoordinate,
            rectHeight.dpToPx() + topCoordinate)
        canvas.drawRect(transparentRect, eraser)

        drawCorners(canvas)
    }

    private fun drawCorners(canvas: Canvas) {
        val offset = cornerPaint.strokeWidth
        val halfOffset = offset / 2
        canvas.drawLine(
            leftCoordinate - offset,
            topCoordinate - halfOffset,
            leftCoordinate - offset + cornerSize,
            topCoordinate - halfOffset,
            cornerPaint
        )
        canvas.drawLine(
            leftCoordinate - halfOffset,
            topCoordinate - offset,
            leftCoordinate - halfOffset,
            topCoordinate - offset + cornerSize,
            cornerPaint
        )
        canvas.drawLine(
            rightCoordinate + offset,
            topCoordinate - halfOffset,
            rightCoordinate + offset - cornerSize,
            topCoordinate - halfOffset,
            cornerPaint
        )
        canvas.drawLine(
            rightCoordinate + halfOffset,
            topCoordinate - offset,
            rightCoordinate + halfOffset,
            topCoordinate - offset + cornerSize,
            cornerPaint
        )
        canvas.drawLine(
            rightCoordinate + halfOffset,
            bottomCoordinate + offset,
            rightCoordinate + halfOffset,
            bottomCoordinate + offset - cornerSize,
            cornerPaint
        )
        canvas.drawLine(
            rightCoordinate + offset,
            bottomCoordinate + halfOffset,
            rightCoordinate + offset - cornerSize,
            bottomCoordinate + halfOffset,
            cornerPaint
        )
        canvas.drawLine(
            leftCoordinate - offset,
            bottomCoordinate + halfOffset,
            leftCoordinate - offset + cornerSize,
            bottomCoordinate + halfOffset,
            cornerPaint
        )
        canvas.drawLine(
            leftCoordinate - halfOffset,
            bottomCoordinate + offset,
            leftCoordinate - halfOffset,
            bottomCoordinate + offset - cornerSize,
            cornerPaint
        )
    }

    private fun calculateRectCoordinates() {
        val metrics = DisplayMetrics()
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(metrics)

        leftCoordinate = (metrics.widthPixels - rectWidth.dpToPx()) / 2f
        topCoordinate = topMargin.dpToPx().toFloat()
        rightCoordinate = leftCoordinate + rectWidth.dpToPx()
        bottomCoordinate = topCoordinate + rectHeight.dpToPx()
    }

    private fun Int.dpToPx(): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()
    }
}