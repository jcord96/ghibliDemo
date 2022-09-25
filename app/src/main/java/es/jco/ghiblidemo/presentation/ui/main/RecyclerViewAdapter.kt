package es.jco.ghiblidemo.presentation.ui.main

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.jco.domain.Film
import es.jco.ghiblidemo.R
import es.jco.ghiblidemo.databinding.ViewMediaItemMainBinding
import es.jco.ghiblidemo.presentation.common.inflate
import kotlin.properties.Delegates

/**
 * Recycler view adapter
 *
 * @property onClick
 * @constructor
 *
 * @param films
 */
class RecyclerViewAdapter(films: List<Film> = emptyList(), private val onClick: (Film, Boolean) -> Unit) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    companion object {
        private val TAG = RecyclerViewAdapter::class.qualifiedName
    }

    private var enableDelete: Boolean = false

    /** Film list */
    var films by Delegates.observable(films) { _, _, _ ->
        Log.i(TAG, "RecyclerView - Film updated")
        notifyDataSetChanged()
    }

    private val viewHolders: MutableList<ViewHolder> = mutableListOf()

    /**
     * On create view holder
     *
     * @param parent
     * @param viewType
     * @return custom view holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_media_item_main)
        return ViewHolder(view)
    }

    /**
     * On bind view holder
     *
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(films[position], enableDelete, onClick)
        viewHolders.add(holder)
    }

    /**
     * Get item count
     *
     * @return list size
     */
    override fun getItemCount(): Int = films.size

    fun enableDelete(enableDelete: Boolean) {
        this.enableDelete = enableDelete
        viewHolders.forEach { it.remove(enableDelete) }
    }

    /**
     * Inner Custom View holder class
     *
     * @constructor
     *
     * @param view
     */
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        /** View binding */
        private val binding = ViewMediaItemMainBinding.bind(view)
        /** Variable to identified the view holder */
        private var film: Film? = null

        private var enableDelete: Boolean = false

        /**
         * Function to bind the film data with the view
         *
         * @param film
         * @param onClick
         * @receiver
         */
        fun bind(film: Film, lastStatusEnableDelete: Boolean, onClick: (Film, Boolean) -> Unit) {
            Log.i(TAG, "ViewHolder - Binding film: ${film.title}")

            // Saving film data internally
            this.film = film
            this.enableDelete = lastStatusEnableDelete

            with(binding) {
                // Binding image of films and actual status of delete button
                Glide.with(view).load(film.image).into(filmCover)
                filmDelete.setVisibility(enableDelete)
                cardView.setOnClickListener { onClick(film, enableDelete) }

                cardView.animation = AnimationUtils.loadAnimation(view.context, R.anim.binding_view_media_item)
            }
        }

        /** External function to delete film for updating internal behaviour */
        fun remove(enableDelete: Boolean) {
            this.enableDelete = enableDelete
            binding.filmDelete.setVisibility(enableDelete)
        }

        private fun ImageButton.setVisibility(enableDelete: Boolean) {
            this.visibility = if (enableDelete) View.VISIBLE else View.INVISIBLE
        }
    }
}