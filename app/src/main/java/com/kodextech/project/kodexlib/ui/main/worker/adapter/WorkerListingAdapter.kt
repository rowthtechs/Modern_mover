package com.kodextech.project.kodexlib.ui.main.worker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kodextech.project.kodexlib.R
import com.kodextech.project.kodexlib.base.BaseActivity
import com.kodextech.project.kodexlib.databinding.WorkerItemBinding
import com.kodextech.project.kodexlib.dialog.AddWorkerDialog
import com.kodextech.project.kodexlib.model.User
import com.kodextech.project.kodexlib.ui.main.dashboard.adapter.Placeholders
import com.kodextech.project.kodexlib.ui.main.dashboard.adapter.loadImage
import com.kodextech.project.kodexlib.utils.gone
import com.kodextech.project.kodexlib.utils.visible

class
WorkerListingAdapter(

    var mContext: BaseActivity,
    var mData: ArrayList<User>,
    var isFor: String,
    var callBack: ((item: User, position: Int, isFor: String) -> Unit)
) : RecyclerView.Adapter<WorkerListingVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerListingVH {
        val view = LayoutInflater.from(mContext).inflate(R.layout.worker_item, parent, false)
        return WorkerListingVH(view)
    }

    override fun onBindViewHolder(holder: WorkerListingVH, position: Int) {
        val mItem = mData[position]


        if (isFor == "0") {
            holder.binding?.ivEdit?.gone()
        } else {
            holder.binding?.ivEdit?.visible()
        }

        holder.binding?.tvWorkerName?.text =
            mItem.profile?.first_name + " " + mItem.profile?.last_name
        holder.binding?.tvWorkerRole?.text = mItem.profile?.worker_type
        holder.binding?.tvWorkerEmail?.text = mItem.email
        holder.binding?.tvWorkerHourly?.text = mItem.profile?.price_amount + " /hr"

        holder.binding?.ivEdit?.setOnClickListener {
            val dialog = AddWorkerDialog.newInstance("Update Worker", "update", mItem.uuid) {
                callBack.invoke(mItem, position, "updated")
            }

            dialog.show(mContext.supportFragmentManager, "")
        }

        holder.binding?.ivCustomer?.loadImage(
            mItem.profile?.profile_image,
            Placeholders.USER,
            true
        )



        holder.itemView.setOnClickListener {
            if (isFor == "0") {

                mData.forEachIndexed { index, customerTypeModel ->
                    if (mData[index].isClicked == false) {
                        mItem.isClicked = true
                    } else {
                        if (index != position) {
                            customerTypeModel.isClicked = false
                        }
                    }
                }
                notifyDataSetChanged()

                callBack(mItem, position, isFor)
            }
        }

        if (isFor == "0") {
            if (mItem.isClicked == true) {
                holder.binding?.cv?.setCardBackgroundColor(mContext.getColor(R.color.blue))
                holder.binding?.tvWorkerName?.setTextColor(mContext.getColor(R.color.white))
                holder.binding?.tvWorkerEmail?.setTextColor(mContext.getColor(R.color.white))
                holder.binding?.tvWorkerRole?.setTextColor(mContext.getColor(R.color.white))
                holder.binding?.labelRole?.setTextColor(mContext.getColor(R.color.white))
                holder.binding?.labelEmail?.setTextColor(mContext.getColor(R.color.white))
                holder.binding?.labelHour?.setTextColor(mContext.getColor(R.color.white))
                holder.binding?.tvWorkerHourly?.setTextColor(mContext.getColor(R.color.white))
            } else {
                holder.binding?.cv?.setCardBackgroundColor(mContext.getColor(R.color.white))
                holder.binding?.tvWorkerName?.setTextColor(mContext.getColor(R.color.cusCol))
                holder.binding?.tvWorkerEmail?.setTextColor(mContext.getColor(R.color.cusCol))
                holder.binding?.tvWorkerRole?.setTextColor(mContext.getColor(R.color.cusCol))
                holder.binding?.labelRole?.setTextColor(mContext.getColor(R.color.locationCol))
                holder.binding?.labelHour?.setTextColor(mContext.getColor(R.color.locationCol))
                holder.binding?.tvWorkerHourly?.setTextColor(mContext.getColor(R.color.cusCol))
                holder.binding?.labelEmail?.setTextColor(mContext.getColor(R.color.locationCol))
            }
        }


    }

    override fun getItemCount() = mData.size
}

class WorkerListingVH(view: View) : RecyclerView.ViewHolder(view) {
    val binding: WorkerItemBinding? = DataBindingUtil.bind(view)
}


