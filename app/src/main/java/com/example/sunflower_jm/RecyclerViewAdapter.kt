package com.example.sunflower_jm

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sunflower_jm.databinding.ItemBinding
import com.example.sunflower_jm.db.DiaryEntity
import com.example.sunflower_jm.detail.DetailActivity

/*
RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>()
리사이클러 뷰를 사용하기 위한 상속
 */
class RecyclerViewAdapter(private val listener : OnItemLongClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private val itemList = mutableListOf<DiaryEntity>()

    fun updateList(items: List<DiaryEntity>) {
        itemList.clear()
        itemList.addAll(items)
    }
    /*
    ViewHolder
    맨 처음 화면에 보여질 10개 정도의 뷰 객체만을 만들고,
    실제 데이터가 100개든 1000개든 원래 만들어 놓은 10개의 객체만 계속 해서 재사용 하는 것
    따라서 10개의 뷰객체를 홀딩하고 있을 객체가 필요한데 그것이 ViewHolder
     */
    class MyViewHolder(binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val item_title = binding.itemTitle
        val item_content = binding.itemContent
        val root = binding.root

        fun bind(item: DiaryEntity) {
            item_title.text = item.title
            item_content.text = item.content
            Log.e("title, content", item.title)

            /*
            putExtra
            Intent를 사용해 액티비티간 이동을 하는 동시에 어떤 값을 넘기고 싶을 때 사용
            key 값과 value 값으로 이동하는 액티비티로 전달되며 다양한 value를 넘길 수 있음

            android task
            Task는 어플리케이션에서 실행되는 액티비티를 보관하고 관리하며 Stack형태의 연속된 Activity로 이루어짐
            Flag를 사용하여 Task내 액티비티의 흐름을 제어할 수 있음

            intent,addFlags
            flag에 적용시키는 함수
            FLAG_ACTIVITY_NEW_TASK는 새로운 task를 생성하여 그 task 안에 액티비티를 추가할 때 사용
             */
            itemView.setOnClickListener {
                Intent(root.context, DetailActivity::class.java).apply {
                    putExtra("data", item)
//                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { root.context.startActivity(this) }
            }
        }
    }

    /*
    ViewHolder가 생성되는 함수
    전체 리스트 목록이 딱 10개라면 위아래 버퍼를 생각해서 13~15개 정도의 뷰 객체가 생성됨
    따라서 13~15번 정도만 호출되고 더 이상 호출되지 않음
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemBinding =
            ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    /*
    생성된 뷰홀더에 데이터를 바인딩 해주는 함수
    데이터가 스크롤 되어 맨 위에 있던 뷰 홀더 객체가 맨 아래로 이동한다면,
    그 레이아웃은 재사용 하되 데이터는 새롭게 바뀔 것임
    아래에서 새롭게 보여질 데이터의 인덱스 값이 position 이라는 이름으로 사용가능
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val diaryData = itemList[position]
        holder.item_title.text = diaryData.title
        holder.item_content.text = diaryData.content
        holder.bind(diaryData)

        holder.root.setOnLongClickListener {
            listener.onLongClick(diaryData)
            false
        }
    }

    /*
    뿌려줄 데이터의 전체 길이를 리턴함
     */
    override fun getItemCount(): Int = itemList.size
}